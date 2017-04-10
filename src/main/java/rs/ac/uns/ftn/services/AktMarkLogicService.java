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
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.generated.DateCreated;
import rs.ac.uns.ftn.model.generated.DateModified;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.security.SecurityUtils;
import rs.ac.uns.ftn.util.XMLUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rs.ac.uns.ftn.constants.XmlNamespaces.*;
import static rs.ac.uns.ftn.constants.XmlSiitGraphNames.AKT_GRAPH_URI;
import static rs.ac.uns.ftn.util.XMLUtil.*;

/**
 * Service for handling XML documents for {@link rs.ac.uns.ftn.model.generated.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@Service
public class AktMarkLogicService implements AktService {

  private static final String AKT_REF = "/akt.xml";

  private static final String AKT_FORMAT = "/akti/%s.xml";

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private final IdentifierGenerator identifierGenerator;

  private final RdfService rdfService;

  private final SPARQLQueryManager sparqlQueryManager;

  private final ValidationService validationService;

  private final Registry<String, Resource> schemaRegistry;

  @Value("classpath:sparql/akt.rq")
  private Resource aktSparql;

  @Value("classpath:sparql/amandmansByAktId.rq")
  private Resource amandmandsByAktSparql;

  @Autowired
  public AktMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator, RdfService rdfService, SPARQLQueryManager sparqlQueryManager, ValidationService validationService, Registry<String, Resource> schemaRegistry) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
    this.validationService = validationService;
    this.schemaRegistry = schemaRegistry;
  }


  @Override
  public Akt findById(String id) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle<Akt> handle = getJaxbHandle(Akt.class);
    documentManager.read(getDocumentId(AKT_FORMAT, id), documentMetadataHandle, handle);


    return handle.get();
  }

  @Override
  public <T> T findById(String id, Class<T> readAs) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);
    return documentManager.readAs(getDocumentId(AKT_FORMAT, id), readAs);
  }


  @Override
  public void removeById(String id) {
    documentManager.delete(getDocumentId(AKT_FORMAT, id));
  }

  @Override
  public List<Akt> findAll(Pageable pageable) {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(AKT_REF);

    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);


    return convertSearchHandle(searchHandle, documentManager, Akt.class);
  }


  @Override
  public void add(Akt akt) {
    final String id = identifierGenerator.generateIdentity();
    akt.setId(id);
    akt.getOtherAttributes().put(new QName("about"), AKT + "/" + id);
    akt.getOtherAttributes().put(new QName("vocab"), PRED);
    akt.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    akt.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    akt.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    final DateCreated dateCreated = new DateCreated();
    dateCreated.setValue(XMLUtil.getToday());
    dateCreated.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumKreiranja");
    dateCreated.getOtherAttributes().put(new QName("datatype"), "xs:datetime");
    akt.getZaglavlje().setDateCreated(dateCreated);

    final DateModified dateModified = new DateModified();
    dateModified.setValue(XMLUtil.getToday());
    dateModified.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumAzuriranja");
    dateModified.getOtherAttributes().put(new QName("datatype"), "xs:datetime");
    akt.getZaglavlje().setDateModified(dateModified);

    Document document = Try.of(() ->
      XMLUtil.toDocument(akt)
    ).getOrElseThrow(ex -> new InvalidServerConfigurationException());

    validationService.validate(document, new Resource[]{schemaRegistry.getItemFromRegistry("akt")});

    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle<Akt> handle = getJaxbHandle(Akt.class);
    handle.set(akt);
    documentManager.write(getDocumentId(AKT_FORMAT, akt.getId()), documentMetadataHandle, handle);
    rdfService.extractAndWriteData(findById(id, Document.class), AKT_GRAPH_URI);
  }

  @Override
  public void deleteAktById(String id) {
    throw new NotImplementedException();
  }

  @Override
  public void deleteAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(AKT_REF);
    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    Arrays.stream(searchHandle.getMatchResults()).map(MatchDocumentSummary::getUri).forEach(documentManager::delete);
    log.info("Deleted all akts");
  }

  @Override
  public List<AktMetadata> getMetadata(Pageable pageable) {

    byte[] data = Try.of(() ->
      Files.readAllBytes(aktSparql.getFile().toPath())
    ).getOrElseThrow(x -> new InvalidServerConfigurationException());


    SPARQLQueryDefinition sparqlQueryDefinition =
      sparqlQueryManager.newQueryDefinition(new String(data))
        .withBinding("user", KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    JacksonHandle jacksonHandle = new JacksonHandle();

    sparqlQueryManager.executeSelect(sparqlQueryDefinition, jacksonHandle);


    List<AktMetadata> metadatas = new ArrayList<>();

    jacksonHandle.get().path("results").path("bindings")
      .forEach(x -> {
        AktMetadata akt = new AktMetadata();
        String[] idparts = x.get("documentId").path("value").asText().split("/");
        akt.setId(idparts[idparts.length - 1]);
        akt.setName(x.get("documentName").path("value").asText());
        akt.setDateCreated(x.get("dateCreated").path("value").asText());
        akt.setDateModified(x.get("dateModified").path("value").asText());
        metadatas.add(akt);
      });


    return metadatas;
  }

  @Override
  public List<AmandmanMetadata> findAktAmandmandsById(String id) {

    byte[] data = Try.of(() ->
      Files.readAllBytes(amandmandsByAktSparql.getFile().toPath())
    ).getOrElseThrow(x -> new InvalidServerConfigurationException());


    SPARQLQueryDefinition sparqlQueryDefinition =
      sparqlQueryManager.newQueryDefinition(new String(data))
        .withBinding("aktId", AKT + "/" + id);

    JacksonHandle jacksonHandle = new JacksonHandle();

    sparqlQueryManager.executeSelect(sparqlQueryDefinition, jacksonHandle);


    List<AmandmanMetadata> metadatas = new ArrayList<>();

    jacksonHandle.get().path("results").path("bindings")
      .forEach(x -> {
        AmandmanMetadata amandman = new AmandmanMetadata();
        String[] idparts = x.get("documentId").path("value").asText().split("/");
        amandman.setId(idparts[idparts.length - 1]);
        amandman.setName(x.get("documentName").path("value").asText());
        amandman.setDateCreated(x.get("dateCreated").path("value").asText());
        amandman.setDateModified(x.get("dateModified").path("value").asText());
        metadatas.add(amandman);
      });
    return metadatas;
  }

  @Override
  public PutAktDTO putAkt(String id, PutAktDTO aktDTO) {
    Akt akt = findById(id);
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("akt", AKT);
    namespaces.put("document", DOCUMENT);
    DocumentPatchBuilder builder = documentManager.newPatchBuilder();
    builder.setNamespaces(namespaces);

    DocumentPatchHandle xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:state", aktDTO.getState()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:result", aktDTO.getResult()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@for", aktDTO.getForVote()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@against", aktDTO.getAgainst()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@notVote", aktDTO.getNotVote()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    return aktDTO;
  }
}
