package rs.ac.uns.ftn.services;

import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import com.marklogic.client.util.EditableNamespaceContext;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanForSednicaDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.AktStates;
import rs.ac.uns.ftn.model.generated.*;
import rs.ac.uns.ftn.security.SecurityUtils;
import rs.ac.uns.ftn.util.XMLUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rs.ac.uns.ftn.constants.AktPredicates.STANJE;
import static rs.ac.uns.ftn.constants.XmlNamespaces.*;
import static rs.ac.uns.ftn.constants.XmlSiitGraphNames.AKT_GRAPH_URI;
import static rs.ac.uns.ftn.constants.XmlSiitGraphNames.SEDNICA_GRAPH_URI;
import static rs.ac.uns.ftn.util.XMLUtil.convertSearchHandle;
import static rs.ac.uns.ftn.util.XMLUtil.getDocumentId;
import static rs.ac.uns.ftn.util.XMLUtil.getJaxbHandle;

/**
 * Created by Micko on 04-Mar-17.
 */
@Slf4j
@Service
public class SednicaMarkLogicService implements SednicaService{

  private static final String SEDNICA_REF = "/sednica.xml";

  private static final String SEDNICA_FORMAT = "/sednice/%s.xml";

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private final IdentifierGenerator identifierGenerator;

  private final RdfService rdfService;

  private final SPARQLQueryManager sparqlQueryManager;

  private final AktService aktService;

  private final AmandmanService amandmanService;

  @Value("classpath:sparql/sednica.rq")
  private Resource sednicaSparql;

  @Value("classpath:sparql/aktsBySednicaId.rq")
  private Resource aktsBySednicaIdSparql;

  @Value("classpath:sparql/amandmandsBySednicaId.rq")
  private Resource amandmandsBySednicaIdSparql;


  @Autowired
  public SednicaMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator, RdfService rdfService, SPARQLQueryManager sparqlQueryManager, AktService aktService, AmandmanService amandmanService) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
    this.aktService = aktService;
    this.amandmanService = amandmanService;
  }


  @Override
  public Sednica findById(String id) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(SEDNICA_REF);

    JAXBHandle<Sednica> handle = getJaxbHandle(Sednica.class);
    documentManager.read(getDocumentId(SEDNICA_FORMAT, id), documentMetadataHandle, handle);


    return handle.get();
  }

  @Override
  public <T> T findById(String id, Class<T> readAs) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(SEDNICA_REF);
    return documentManager.readAs(getDocumentId(SEDNICA_FORMAT, id), readAs);
  }


  @Override
  public void removeById(String id) {
    documentManager.delete(getDocumentId(SEDNICA_FORMAT, id));
  }

  @Override
  public List<Sednica> findAll(Pageable pageable) {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(SEDNICA_REF);

    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);


    return convertSearchHandle(searchHandle, documentManager, Sednica.class);
  }


  @Override
  public void add(Sednica sednica, String[] akti, String[] amandmani) {
    final String id = identifierGenerator.generateIdentity();
    sednica.setId(id);
    sednica.getOtherAttributes().put(new QName("about"), SEDNICA + "/" + id);
    sednica.getOtherAttributes().put(new QName("vocab"), PRED);
    sednica.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    sednica.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    sednica.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    sednica.getInformacije().setZavrsena(false);

    final DateCreated dateCreated = new DateCreated();
    dateCreated.setValue(XMLUtil.getToday());
    dateCreated.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumKreiranja");
    dateCreated.getOtherAttributes().put(new QName("datatype"), "xs:date");
    sednica.getZaglavljeSednica().setDateCreated(dateCreated);

    final DateModified dateModified = new DateModified();
    dateModified.setValue(XMLUtil.getToday());
    dateModified.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumAzuriranja");
    dateModified.getOtherAttributes().put(new QName("datatype"), "xs:date");
    sednica.getZaglavljeSednica().setDateModified(dateModified);

    sednica.getZaglavljeSednica().getNaziv().getOtherAttributes().put(new QName("property"), PRED_PREF + ":imeDokumenta");
    sednica.getZaglavljeSednica().getNaziv().getOtherAttributes().put(new QName("datatype"), "xs:string");

    sednica.setAkti(new Akti());

    for(String aktId : akti){
      Akti.AktRef ref = new Akti.AktRef();
      ref.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":pripada");
      ref.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":akt");
      ref.getOtherAttributes().put(new QName("href"), AKT + "/" + aktId);
      rdfService.updateTripleAkt(aktId, AktStates.RAZMATRAN, STANJE, AKT_GRAPH_URI);
      sednica.getAkti().getAktRef().add(ref);
    }

    sednica.setAmandmani(new Amandmani());

    for(String amId : amandmani){
      Amandmani.AmandmanRef ref = new Amandmani.AmandmanRef();
      ref.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":pripada");
      ref.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":amandman");
      ref.getOtherAttributes().put(new QName("href"), AMANDMAN + "/" + amId);
      sednica.getAmandmani().getAmandmanRef().add(ref);
    }


    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(SEDNICA_REF);

    JAXBHandle<Sednica> handle = getJaxbHandle(Sednica.class);
    handle.set(sednica);
    documentManager.write(getDocumentId(SEDNICA_FORMAT, sednica.getId()), documentMetadataHandle, handle);

    Document document = findById(id, Document.class);

    rdfService.extractAndWriteData(document, SEDNICA_GRAPH_URI);

  }

  @Override
  public void deleteById(String id) {
    throw new NotImplementedException();
  }

  @Override
  public void deleteAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(SEDNICA_REF);
    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    Arrays.stream(searchHandle.getMatchResults()).map(MatchDocumentSummary::getUri).forEach(documentManager::delete);
    log.info("Deleted all sednicas");
  }


  @Override
  public List<SednicaDTO> getMetadata(Pageable pageable) {
    byte[] data = Try.of(() ->
      Files.readAllBytes(sednicaSparql.getFile().toPath())
    ).getOrElseThrow(x -> new InvalidServerConfigurationException());

    SPARQLQueryDefinition sparqlQueryDefinition =
      sparqlQueryManager.newQueryDefinition(new String(data))
        .withBinding("user", KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    JacksonHandle jacksonHandle = new JacksonHandle();

    sparqlQueryManager.executeSelect(sparqlQueryDefinition, jacksonHandle);


    List<SednicaDTO> sednice = new ArrayList<>();

    jacksonHandle.get().path("results").path("bindings")
      .forEach(x -> {
        SednicaDTO sednicaDTO = new SednicaDTO();
        String[] idparts = x.get("documentId").path("value").asText().split("/");
        sednicaDTO.setId(idparts[idparts.length - 1]);
        sednicaDTO.setNaziv(x.get("documentName").path("value").asText());
        Sednica sednica = findById(sednicaDTO.getId());
        sednicaDTO.setDatum(sednica.getInformacije().getDatum());
        sednicaDTO.setMesto(sednica.getInformacije().getMesto());
        sednice.add(sednicaDTO);
      });

    return sednice;
  }

  @Override
  public List<PutAktDTO> findSednicaAktsById(String id) {
    byte[] data = Try.of(() ->
      Files.readAllBytes(aktsBySednicaIdSparql.getFile().toPath())
    ).getOrElseThrow(x -> new InvalidServerConfigurationException());

    SPARQLQueryDefinition sparqlQueryDefinition =
      sparqlQueryManager.newQueryDefinition(new String(data))
        .withBinding("documentId", SEDNICA + "/" + id);

    JacksonHandle jacksonHandle = new JacksonHandle();

    sparqlQueryManager.executeSelect(sparqlQueryDefinition, jacksonHandle);

    List<PutAktDTO> akti = new ArrayList<>();

    jacksonHandle.get().path("results").path("bindings")
      .forEach(x -> {
        System.out.println(x.toString());
        PutAktDTO aktDTO = new PutAktDTO();
        String[] idparts = x.get("aktId").path("value").asText().split("/");
        String aktId = idparts[idparts.length - 1];
        Akt akt = aktService.findById(aktId);
        aktDTO.setId(akt.getId());
        aktDTO.setName(akt.getZaglavlje().getNaziv().getValue());
        aktDTO.setState(akt.getDocumentAktRef().getDocument().getState());
        aktDTO.setAgainst(akt.getDocumentAktRef().getDocument().getResults().getAgainst());
        aktDTO.setForVote(akt.getDocumentAktRef().getDocument().getResults().getFor());
        aktDTO.setNotVote(akt.getDocumentAktRef().getDocument().getResults().getNotVote());
        aktDTO.setResult(akt.getDocumentAktRef().getDocument().getResult());
        akti.add(aktDTO);
      });

    return akti;
  }

  @Override
  public List<AmandmanForSednicaDTO> findSednicaAmandmandsById(String id) {
    byte[] data = Try.of(() ->
      Files.readAllBytes(amandmandsBySednicaIdSparql.getFile().toPath())
    ).getOrElseThrow(x -> new InvalidServerConfigurationException());

    SPARQLQueryDefinition sparqlQueryDefinition =
      sparqlQueryManager.newQueryDefinition(new String(data))
        .withBinding("documentId", SEDNICA + "/" + id);

    JacksonHandle jacksonHandle = new JacksonHandle();

    sparqlQueryManager.executeSelect(sparqlQueryDefinition, jacksonHandle);

    List<AmandmanForSednicaDTO> amandmands = new ArrayList<>();

    jacksonHandle.get().path("results").path("bindings")
      .forEach(x -> {
        System.out.println(x.toString());
        AmandmanForSednicaDTO amandmanDTO = new AmandmanForSednicaDTO();
        String[] idparts = x.get("amandmanId").path("value").asText().split("/");
        String amandmanId = idparts[idparts.length - 1];
        Amandman amandman = amandmanService.findById(amandmanId);
        amandmanDTO.setId(amandman.getId());
        amandmanDTO.setNaziv(amandman.getZaglavljeAmandman().getNaziv().getValue());
        amandmanDTO.setAktId(amandman.getAktId());
        amandmanDTO.setState(amandman.getDocumentAmRef().getDocument().getState());
        amandmanDTO.setAgainst(amandman.getDocumentAmRef().getDocument().getResults().getAgainst());
        amandmanDTO.setForVote(amandman.getDocumentAmRef().getDocument().getResults().getFor());
        amandmanDTO.setNotVote(amandman.getDocumentAmRef().getDocument().getResults().getNotVote());
        amandmanDTO.setResult(amandman.getDocumentAmRef().getDocument().getResult());
        amandmands.add(amandmanDTO);
      });

    return amandmands;
  }

  @Override
  public void putSednica(String id, SednicaDTO sednicaDTO) {
    Sednica sednica = findById(id);
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("sednica", SEDNICA);
    DocumentPatchBuilder builder = documentManager.newPatchBuilder();
    builder.setNamespaces(namespaces);

    DocumentPatchHandle xmlPatch = builder.replaceValue("//sednica:sednica/sednica:informacije/@zavrsena ", sednicaDTO.getZavrsena()).build();
    documentManager.patch(getDocumentId(SEDNICA_FORMAT, sednica.getId()), xmlPatch);
  }
}
