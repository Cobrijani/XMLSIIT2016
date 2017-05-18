package rs.ac.uns.ftn.services;

import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.io.*;
import com.marklogic.client.io.marker.SPARQLResultsReadHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import com.marklogic.client.util.EditableNamespaceContext;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.generated.DateCreated;
import rs.ac.uns.ftn.model.generated.DateModified;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.security.SecurityUtils;
import rs.ac.uns.ftn.util.XMLUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

  private final Registry<String, Resource> aktSparqlRegistry;


  @Value("classpath:sparql/amandmansByAktId.rq")
  private Resource amandmandsByAktSparql;

  @Autowired
  public AktMarkLogicService(XMLDocumentManager documentManager,
                             QueryManager queryManager,
                             IdentifierGenerator identifierGenerator,
                             RdfService rdfService,
                             SPARQLQueryManager sparqlQueryManager,
                             ValidationService validationService,
                             @Qualifier("XmlSchemaRegistry") Registry<String, Resource> schemaRegistry,
                             @Qualifier("AktSparqlQueryRegistry") Registry<String, Resource> aktSparqlRegistry) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
    this.validationService = validationService;
    this.schemaRegistry = schemaRegistry;
    this.aktSparqlRegistry = aktSparqlRegistry;
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
    dateCreated.getOtherAttributes().put(new QName("datatype"), XS_PREF + ":dateTime");
    akt.getZaglavlje().setDateCreated(dateCreated);

    final DateModified dateModified = new DateModified();
    dateModified.setValue(XMLUtil.getToday());
    dateModified.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumAzuriranja");
    dateModified.getOtherAttributes().put(new QName("datatype"), XS_PREF + ":dateTime");
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
    return getMetadata(pageable, null);
  }

  private <T extends SPARQLResultsReadHandle> T getMetadata(Pageable pageable, AktMetadataPredicate aktMetadataPredicate, T handle) {
    sparqlQueryManager.setPageLength(pageable.getPageSize());


    String query = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#>\n" +
      "\n" +
      "SELECT * FROM <skupstina/dokument/akt>\n" +
      "WHERE {\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/napravio> ?user .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/imeDokumenta> ?documentName .\n" +
      "  ?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumKreiranja> ?dateCreated .\n" +
      "  ?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumAzuriranja> ?dateModified .\n" +
      "  FILTER( regex(?documentName, ?search ))\n";

    StringBuilder queryBuilder = new StringBuilder(query);

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getDateCreatedFromTimestamp)
      .map(XMLUtil::toXmlCalendar)
      .ifPresent(x -> queryBuilder.append("FILTER(?dateCreated >= \"").append(x.toXMLFormat()).append("\"^^xs:dateTime)\n "));

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getDateCreatedToTimestamp)
      .map(XMLUtil::toXmlCalendar).ifPresent(x ->
      queryBuilder.append("FILTER(?dateCreated <= \"").append(x.toXMLFormat()).append("\"^^xs:dateTime) \n"));

    queryBuilder.append("}");

    SPARQLQueryDefinition sparqlQueryDefinition =
      Try.of(() ->
        sparqlQueryManager
          .newQueryDefinition(new StringHandle(queryBuilder.toString()))
      ).getOrElseThrow(x -> new InvalidServerConfigurationException());


    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::isOwned)
      .ifPresent(self -> {
        if (self) {
          sparqlQueryDefinition
            .withBinding("user", KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());
        }
      });


    String search = Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getSearchQuery)
      .orElse("");

    sparqlQueryDefinition
      .withBinding("search", search);


    sparqlQueryManager.executeSelect(sparqlQueryDefinition, handle, pageable.getOffset() + 1);

    return handle;
  }

  @Override
  public List<AktMetadata> getMetadata(Pageable pageable, AktMetadataPredicate aktMetadataPredicate) {
    JacksonHandle jacksonHandle = getMetadata(pageable, aktMetadataPredicate, new JacksonHandle());

    List<AktMetadata> metadatas = new ArrayList<>();

    Optional.of(jacksonHandle)
      .map(JacksonHandle::get)
      .map(y -> y.path("results").path("bindings"))
      .ifPresent(x -> x.forEach(node -> {
        AktMetadata akt = new AktMetadata();
        String[] idparts = node.get("documentId").path("value").asText().split("/");
        akt.setId(idparts[idparts.length - 1]);
        akt.setName(node.get("documentName").path("value").asText());
        akt.setDateCreated(node.get("dateCreated").path("value").asText());
        akt.setDateModified(node.get("dateModified").path("value").asText());
        metadatas.add(akt);
      }));


    return metadatas;
  }

  @Override
  public Page<AktMetadata> getMetadataPage(Pageable pageable) {
    return getMetadataPage(pageable, null);
  }

  @Override
  public Page<AktMetadata> getMetadataPage(Pageable pageable, AktMetadataPredicate aktMetadataPredicate) {
    List<AktMetadata> content = getMetadata(pageable, aktMetadataPredicate);

    SPARQLQueryDefinition countSparqlQueryDefinition =
      Try.of(() ->
        sparqlQueryManager
          .newQueryDefinition(new FileHandle(aktSparqlRegistry.getItemFromRegistry("aktCount.rq").getFile()))
      ).getOrElseThrow(x -> new InvalidServerConfigurationException());

    countSparqlQueryDefinition.withBinding("user", KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    JacksonHandle jacksonHandle = new JacksonHandle();
    sparqlQueryManager.executeSelect(countSparqlQueryDefinition, jacksonHandle);
    int size = content.size();
    size = Optional.of(jacksonHandle)
      .map(JacksonHandle::get)
      .map(y -> y.path("results").path("bindings"))
      .map(x -> x.get(0).get("count").path("value").asInt())
      .orElseThrow(InvalidServerConfigurationException::new);

    return new PageImpl<AktMetadata>(content, pageable, size);
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
  public String getSparqlResult(Pageable pageable, AktMetadataPredicate aktMetadataPredicate) {

    StringHandle handle = new StringHandle();
    handle.setMimetype(SPARQLMimeTypes.SPARQL_XML);
    handle = getMetadata(pageable, aktMetadataPredicate, handle);

    return handle.get();
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

    Akt aktDb = findById(akt.getId());

    if(aktDTO.getForVote() != null) {
      xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@for", aktDb.getDocumentAktRef().getDocument().getResults().getFor() + 1).build();
      documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);
    }else if(aktDTO.getAgainst() != null){
      xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@against", aktDb.getDocumentAktRef().getDocument().getResults().getAgainst() + 1).build();
      documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);
    }

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@notVote", aktDTO.getNotVote()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    return aktDTO;
  }
}
