package rs.ac.uns.ftn.services;

import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.*;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.io.marker.SPARQLResultsReadHandle;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.dto.amandman.AmandmanForSednicaDTO;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.AmandmanMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.model.generated.DateCreated;
import rs.ac.uns.ftn.model.generated.DateModified;
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
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.constants.XmlNamespaces.*;
import static rs.ac.uns.ftn.constants.XmlSiitGraphNames.AMANDMAN_GRAPH_URI;
import static rs.ac.uns.ftn.util.XMLUtil.convertSearchHandle;
import static rs.ac.uns.ftn.util.XMLUtil.getDocumentId;
import static rs.ac.uns.ftn.util.XMLUtil.getJaxbHandle;

/**
 * Service for handling XML documents for {@link rs.ac.uns.ftn.model.generated.Amandman}
 * Created by Arsa on 07-Mar-17.
 */
@Slf4j
@Service
public class AmandmanMarkLogicService implements AmandmanService{

  private static final String AMANDMAN_REF = "/amandman.xml";

  private static final String AMANDMAN_FORMAT = "/amandmani/%s.xml";

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private final IdentifierGenerator identifierGenerator;

  private final RdfService rdfService;

  private final SPARQLQueryManager sparqlQueryManager;

  private final Registry<String, Resource> amandmanSparqlRegistry;

  @Value("classpath:sparql/amandman.rq")
  private Resource amandmanSparql;

  @Autowired
  public AmandmanMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator,
                                  RdfService rdfService, SPARQLQueryManager sparqlQueryManager,
                                  @Qualifier("AmandmanSparqlQueryRegistry") Registry<String, Resource> amandmanSparqlRegistry) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
    this.amandmanSparqlRegistry = amandmanSparqlRegistry;
  }


  @Override
  public Amandman findById(String id) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AMANDMAN_REF);

    JAXBHandle<Amandman> handle = getJaxbHandle(Amandman.class);
    documentManager.read(getDocumentId(AMANDMAN_FORMAT, id), documentMetadataHandle, handle);

    return handle.get();
  }

  @Override
  public <T> T findById(String id, Class<T> readAs) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AMANDMAN_REF);
    return documentManager.readAs(getDocumentId(AMANDMAN_FORMAT, id), readAs);
  }

  @Override
  public void removeById(String id) {
    documentManager.delete(getDocumentId(AMANDMAN_FORMAT, id));
  }

  @Override
  public List<Amandman> findAll(Pageable pageable) {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(AMANDMAN_REF);

    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);


    return convertSearchHandle(searchHandle, documentManager, Amandman.class);
  }

  @Override
  public List<Amandman> findAllContaining(Pageable pageable, String term) {
    final Optional<String> optTerm = Optional.ofNullable(term);

    final StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
//    StructuredQueryDefinition definition =
//      sb.and(sb.collection(AMANDMAN_REF), sb.properties(sb.term(optTerm.orElse(""))));
    final String terms[] = optTerm.map(x -> x.split(" "))
      .orElse(new String[0]);
    final StructuredQueryDefinition definition = sb.term(terms);
    definition.setCollections(AMANDMAN_REF);

    final SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    return convertSearchHandle(searchHandle, documentManager, Amandman.class);
  }

  @Override
  public void add(Amandman amandman) {
    final String id = identifierGenerator.generateIdentity();
    amandman.setId(id);

    amandman.getOtherAttributes().put(new QName("about"), AMANDMAN + "/" + id);
    amandman.getOtherAttributes().put(new QName("vocab"), PRED);
    amandman.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    amandman.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    amandman.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    amandman.getZaglavljeAmandman().getAktRef().getOtherAttributes().put(new QName("about"), AMANDMAN + "/" + id);
    amandman.getZaglavljeAmandman().getAktRef().getOtherAttributes().put(new QName("vocab"), PRED);
    amandman.getZaglavljeAmandman().getAktRef().getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":akt");
    amandman.getZaglavljeAmandman().getAktRef().getOtherAttributes().put(new QName("rel"), PRED_PREF + ":menja");
    amandman.getZaglavljeAmandman().getAktRef().getOtherAttributes().put(new QName("href"), AKT + "/" + amandman.getAktId());

    final DateCreated dateCreated = new DateCreated();
    dateCreated.setValue(XMLUtil.getToday());
    dateCreated.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumKreiranja");
    dateCreated.getOtherAttributes().put(new QName("datatype"), "xs:date");
    amandman.getZaglavljeAmandman().setDateCreated(dateCreated);

    final DateModified dateModified = new DateModified();
    dateModified.setValue(XMLUtil.getToday());
    dateModified.getOtherAttributes().put(new QName("property"), PRED_PREF + ":datumAzuriranja");
    dateModified.getOtherAttributes().put(new QName("datatype"), "xs:date");
    amandman.getZaglavljeAmandman().setDateModified(dateModified);

    amandman.getZaglavljeAmandman().getNaziv().getOtherAttributes().put(new QName("property"), PRED_PREF + ":imeDokumenta");
    amandman.getZaglavljeAmandman().getNaziv().getOtherAttributes().put(new QName("datatype"), "xs:string");


    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AMANDMAN_REF);

    JAXBHandle<Amandman> handle = getJaxbHandle(Amandman.class);
    handle.set(amandman);
    documentManager.write(getDocumentId(AMANDMAN_FORMAT, amandman.getId()), documentMetadataHandle, handle);

    Document document = findById(id, Document.class);

    rdfService.extractAndWriteData(document, AMANDMAN_GRAPH_URI);
  }

  @Override
  public void deleteAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(AMANDMAN_REF);
    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    Arrays.stream(searchHandle.getMatchResults()).map(MatchDocumentSummary::getUri).forEach(documentManager::delete);
    log.info("Izbrisani svi amandmani");
  }

  @Override
  public List<AmandmanMetadata> getMetadata(Pageable pageable)  {
    return getMetadata(pageable, null);
  }


  private <T extends SPARQLResultsReadHandle> T getMetadata(Pageable pageable, AmandmanMetadataPredicate amandmanMetadataPredicate, T handle) {
    sparqlQueryManager.setPageLength(pageable.getPageSize());

    List<String> amandmanIds =
      Optional.ofNullable(amandmanMetadataPredicate)
        .map(x -> findAllContaining(pageable, amandmanMetadataPredicate.getSearchQuery())
          .stream().map(Amandman::getId).collect(Collectors.toList())).orElse(new ArrayList<>());

    String query = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#>\n" +
      "\n" +
      "SELECT * FROM <skupstina/dokument/amandman>\n" +
      "WHERE {\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/napravio> ?user .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/imeDokumenta> ?documentName .\n" +
      "  ?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumKreiranja> ?dateCreated .\n" +
      "  ?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumAzuriranja> ?dateModified .\n";


    final StringBuilder queryBuilder = new StringBuilder(query);

    queryBuilder.append("FILTER( regex(?documentName, ?search )");
    String s = amandmanIds.stream().map(x ->
      String.format("|| ?documentId = <http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman/%s>", x)
    ).collect(Collectors.joining());
    queryBuilder.append(s);
    queryBuilder.append(")\n");

    Optional.ofNullable(amandmanMetadataPredicate)
      .map(AmandmanMetadataPredicate::getDateCreatedFromTimestamp)
      .map(XMLUtil::toXmlCalendar)
      .ifPresent(x ->
        queryBuilder.append("FILTER(xs:dateTime(?dateCreated) >= xs:dateTime(\"").append(x.toXMLFormat()).append("\"))\n "));

    Optional.ofNullable(amandmanMetadataPredicate)
      .map(AmandmanMetadataPredicate::getDateCreatedToTimestamp)
      .map(XMLUtil::toXmlCalendar).ifPresent(x ->
      queryBuilder.append("FILTER(xs:dateTime(?dateCreated) <= xs:dateTime(\"").append(x.toXMLFormat()).append("\")) \n"));

    queryBuilder.append("}");

    SPARQLQueryDefinition sparqlQueryDefinition =
      Try.of(() ->
        sparqlQueryManager
          .newQueryDefinition(new StringHandle(queryBuilder.toString()))
      ).getOrElseThrow(x -> new InvalidServerConfigurationException());


    Optional.ofNullable(amandmanMetadataPredicate)
      .map(AmandmanMetadataPredicate::isOwned)
      .ifPresent(self -> {
        if (self) {
          sparqlQueryDefinition
            .withBinding("user", KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());
        }
      });


    String search = Optional.ofNullable(amandmanMetadataPredicate)
      .map(AmandmanMetadataPredicate::getSearchQuery)
      .orElse("");

    sparqlQueryDefinition
      .withBinding("search", search);


    sparqlQueryManager.executeSelect(sparqlQueryDefinition, handle, pageable.getOffset() + 1);

    return handle;
  }

  @Override
  public List<AmandmanMetadata> getMetadata(Pageable pageable, AmandmanMetadataPredicate amandmanMetadataPredicate) {
    JacksonHandle jacksonHandle = getMetadata(pageable, amandmanMetadataPredicate, new JacksonHandle());

    List<AmandmanMetadata> metadatas = new ArrayList<>();

    Optional.of(jacksonHandle)
      .map(JacksonHandle::get)
      .map(y -> y.path("results").path("bindings"))
      .ifPresent(x -> x.forEach(node -> {
        AmandmanMetadata amandman = new AmandmanMetadata();
        String[] idparts = node.get("documentId").path("value").asText().split("/");

        amandman.setId(idparts[idparts.length - 1]);
        amandman.setName(node.get("documentName").path("value").asText());
        String userPath = node.get("user").path("value").asText();
        amandman.setUser(userPath.substring(userPath.lastIndexOf('/')+1,userPath.length()));
        amandman.setDateCreated(node.get("dateCreated").path("value").asText());
        amandman.setDateModified(node.get("dateModified").path("value").asText());
        metadatas.add(amandman);
      }));

    return metadatas;
  }

  @Override
  public Page<AmandmanMetadata> getMetadataPage(Pageable pageable, AmandmanMetadataPredicate amandmanMetadataPredicate) {
    List<AmandmanMetadata> content = getMetadata(pageable, amandmanMetadataPredicate);

    SPARQLQueryDefinition countSparqlQueryDefinition =
      Try.of(() ->
        sparqlQueryManager
          .newQueryDefinition(new FileHandle(amandmanSparqlRegistry.getItemFromRegistry("amandmanCount.rq").getFile()))
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

    return new PageImpl<AmandmanMetadata>(content, pageable, size);
  }

  @Override
  public AmandmanForSednicaDTO putAmandman(String id, AmandmanForSednicaDTO amDTO) {
    Amandman am = findById(id);
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("am", AMANDMAN);
    namespaces.put("document", DOCUMENT);
    DocumentPatchBuilder builder = documentManager.newPatchBuilder();
    builder.setNamespaces(namespaces);

    DocumentPatchHandle xmlPatch = builder.replaceValue("//am:amandman/am:document_am_ref/document:document/document:state", amDTO.getState()).build();
    documentManager.patch(getDocumentId(AMANDMAN_FORMAT, am.getId()), xmlPatch);

    xmlPatch = builder.replaceValue("//am:amandman/am:document_am_ref/document:document/document:result", amDTO.getResult()).build();
    documentManager.patch(getDocumentId(AMANDMAN_FORMAT, am.getId()), xmlPatch);

    Amandman amandman = findById(id);

    if(amDTO.getForVote() != null) {
      xmlPatch = builder.replaceValue("//am:amandman/am:document_am_ref/document:document/document:results/@for", amandman.getDocumentAmRef().getDocument().getResults().getFor() + 1).build();
      documentManager.patch(getDocumentId(AMANDMAN_FORMAT, am.getId()), xmlPatch);
    }else if(amDTO.getAgainst() != null){
      xmlPatch = builder.replaceValue("//am:amandman/am:document_am_ref/document:document/document:results/@against", amandman.getDocumentAmRef().getDocument().getResults().getAgainst() + 1).build();
      documentManager.patch(getDocumentId(AMANDMAN_FORMAT, am.getId()), xmlPatch);
    }


    xmlPatch = builder.replaceValue("//am:amandman/am:document_am_ref/document:document/document:results/@notVote", amDTO.getNotVote()).build();
    documentManager.patch(getDocumentId(AMANDMAN_FORMAT, am.getId()), xmlPatch);

    return amDTO;
  }
}
