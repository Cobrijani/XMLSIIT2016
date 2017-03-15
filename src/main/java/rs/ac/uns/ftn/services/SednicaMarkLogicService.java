package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.generated.DateCreated;
import rs.ac.uns.ftn.model.generated.DateModified;
import rs.ac.uns.ftn.model.generated.Sednica;
import rs.ac.uns.ftn.model.metadata.SednicaMetadata;
import rs.ac.uns.ftn.security.SecurityUtils;
import rs.ac.uns.ftn.util.XMLUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rs.ac.uns.ftn.constants.XmlNamespaces.*;
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

  @Value("classpath:sparql/sednica.rq")
  private Resource sednicaSparql;


  @Autowired
  public SednicaMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator, RdfService rdfService, SPARQLQueryManager sparqlQueryManager) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
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
  public void add(Sednica sednica) {
    final String id = identifierGenerator.generateIdentity();
    sednica.setId(id);
    sednica.getOtherAttributes().put(new QName("about"), SEDNICA + "/" + id);
    sednica.getOtherAttributes().put(new QName("vocab"), PRED);
    sednica.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    sednica.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    sednica.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

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
        System.out.println(x.toString());
        SednicaDTO sednicaDTO = new SednicaDTO();
        String[] idparts = x.get("documentId").path("value").asText().split("/");
        sednicaDTO.setId(idparts[idparts.length - 1]);
        sednicaDTO.setNaziv(x.get("documentName").path("value").asText());
//        sednica.setDatum(x.get("datum").path("value").asText());
//        sednica.setMesto(x.get("mesto").path("value").asText());
        Sednica sednica = findById(sednicaDTO.getId());
        sednicaDTO.setDatum(sednica.getInformacije().getDatum().toString());
        sednicaDTO.setMesto(sednica.getInformacije().getMesto());
        sednice.add(sednicaDTO);
      });

    return sednice;
  }
}
