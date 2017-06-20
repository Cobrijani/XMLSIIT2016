package rs.ac.uns.ftn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.Transaction;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.*;
import com.marklogic.client.io.marker.DocumentPatchHandle;
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
import rs.ac.uns.ftn.exceptions.ForbiddenUserException;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.AktStates;
import rs.ac.uns.ftn.model.generated.*;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.security.SecurityUtils;
import rs.ac.uns.ftn.util.XMLUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.constants.AktPredicates.STANJE;
import static rs.ac.uns.ftn.constants.AktPredicates.VERZIJA;
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

  private final DatabaseClient databaseClient;


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
                             @Qualifier("AktSparqlQueryRegistry") Registry<String, Resource> aktSparqlRegistry,
                             DatabaseClient databaseClient) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
    this.sparqlQueryManager = sparqlQueryManager;
    this.validationService = validationService;
    this.schemaRegistry = schemaRegistry;
    this.aktSparqlRegistry = aktSparqlRegistry;
    this.databaseClient = databaseClient;
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
  public List<Akt> findAll(Pageable pageable) {
    return findAllContaining(pageable, null);
  }

  @Override
  public List<Akt> findAllContaining(Pageable pageable, String term) {
    final Optional<String> optTerm = Optional.ofNullable(term);

    final StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    final String terms[] = optTerm.map(x -> x.split(" "))
      .orElse(new String[0]);
    final StructuredQueryDefinition definition = sb.term(terms);
    definition.setCollections(AKT_REF);

    final SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    return convertSearchHandle(searchHandle, documentManager, Akt.class);
  }


  @Override
  public Akt add(Akt akt) {
    final String id = identifierGenerator.generateIdentity();
    akt.setId(id);
    akt.getOtherAttributes().put(new QName("about"), AKT + "/" + id);
    akt.getOtherAttributes().put(new QName("vocab"), PRED);
    akt.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    akt.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    akt.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());

    final State gs = new State();
    akt.getDocumentAktRef().getDocument().setGraphState(new GraphState());
    gs.setValue(AktStates.NOV);
    gs.getOtherAttributes().put(new QName("property"), PRED_PREF + ":stanje");
    gs.getOtherAttributes().put(new QName("datatype"), XS_PREF + ":string");
    akt.getDocumentAktRef().getDocument().getGraphState().setState(gs);

    final Version gv = new Version();
    gv.setValue(AktStates.NOV);
    gv.getOtherAttributes().put(new QName("property"), PRED_PREF + ":verzija");
    gv.getOtherAttributes().put(new QName("datatype"), XS_PREF + ":string");
    akt.getDocumentAktRef().getDocument().getGraphState().setVersion(gv);

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
    return akt;
  }

  private boolean isOwner(String id) {

    final Resource resource = aktSparqlRegistry.getItemFromRegistry("aktCount.rq");
    final JacksonHandle handle = new JacksonHandle();

    final FileHandle fileHandle =
      Try.of(() -> new FileHandle(resource.getFile()))
        .getOrElseThrow((Function<Throwable, InvalidServerConfigurationException>)
          InvalidServerConfigurationException::new);

    SPARQLQueryDefinition q = sparqlQueryManager.newQueryDefinition(fileHandle);
    q.withBinding("s", String.format("%s/%s", AKT, id));
    q.withBinding("user", String.format("%s/%s", KORISNIK, SecurityUtils.getCurrentUserLogin()));

    sparqlQueryManager.executeSelect(q, handle);

    return readCountRdfResult(handle) == 1;
  }

  /**
   * Checks whether akt with given id can be deleted
   *
   * @param id Akt id
   * @return boolean can be deleted or not
   */
  private boolean canBeDeleted(String id) {
    final FileHandle fileHandle =
      Try.of(() -> new FileHandle(aktSparqlRegistry.getItemFromRegistry("aktCanBeDeleted.rq").getFile()))
        .getOrElseThrow(x -> new InvalidServerConfigurationException("Cannot read aktCanBeDeleted.rq"));

    SPARQLQueryDefinition q = sparqlQueryManager.newQueryDefinition(fileHandle);
    q.withBinding("user", String.format("%s/%s", KORISNIK, SecurityUtils.getCurrentUserLogin()));
    q.withBinding("s", String.format("%s/%s", AKT, id));

    final JacksonHandle handle = new JacksonHandle();
    sparqlQueryManager.executeSelect(q, handle);

    return readCountRdfResult(handle) == 1;
  }

  @Override
  public void deleteAktById(String id) {
    if (!isOwner(id)) {
      log.error("User with login: {} cannot delete akt with id: {}", SecurityUtils.getCurrentUserLogin(), id);
      throw new ForbiddenUserException(
        String.format("User with login: %s cannot delete akt with id: %s", SecurityUtils.getCurrentUserLogin(), id));
    }

    if (!canBeDeleted(id)) {
      log.error("Akt with id: {} cannot be deleted. State change permission denied.", id);
      throw new ForbiddenUserException(
        String.format("Akt with id: %s cannot be deleted. State change permission denied", id));
    }

    final Transaction transaction = databaseClient.openTransaction();
    log.info("Opened transaction for deleting akt with id: {} and transaction id {}",
      id, transaction.getTransactionId());

    final XMLDocumentManager documentManager = databaseClient.newXMLDocumentManager();

    try {
      documentManager.delete(getDocumentId(AKT_FORMAT, id), transaction);
    } catch (Exception e) {
      log.error("Error deleting document akt with id: {}, rollback in progress", id);
      transaction.rollback();
    }

    log.info("Successfully deleted document with id: {}", id);
    try {
      rdfService.updateTripleAkt(id, AktStates.POVUCEN, STANJE, AKT_GRAPH_URI, transaction);
      transaction.commit();
      log.info("Complete delete successful for document with id: {} commit success", id);
    } catch (Exception e) {
      log.error("Error deleting tripple store for document with id {}, rollbacking", id);
      transaction.rollback();
    }
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

    List<String> aktIds =
      Optional.ofNullable(aktMetadataPredicate)
        .map(x -> findAllContaining(pageable, aktMetadataPredicate.getSearchQuery())
          .stream().map(Akt::getId).collect(Collectors.toList())).orElse(new ArrayList<>());


    String query = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#>\n" +
      "\n" +
      "SELECT * FROM <skupstina/dokument/akt>\n" +
      "WHERE {\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/napravio> ?user .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/imeDokumenta> ?documentName .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumKreiranja> ?dateCreated .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/datumAzuriranja> ?dateModified .\n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/stanje> ?stanje. \n" +
      "\t?documentId <http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/verzija> ?verzija.\n";


    final StringBuilder queryBuilder = new StringBuilder(query);

    queryBuilder.append("FILTER( regex(?documentName, ?search )");
    String s = aktIds.stream().map(x ->
      String.format("|| ?documentId = <http://parlament.gov.rs/rs.ac.uns.ftn.model.akt/%s>", x)
    ).collect(Collectors.joining());
    queryBuilder.append(s);
    queryBuilder.append(")\n");

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getAktState)
      .ifPresent(x ->
        queryBuilder.append("FILTER(regex(?stanje, ?searchStanje)) \n"));

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getDateCreatedFromTimestamp)
      .map(XMLUtil::toXmlCalendar)
      .ifPresent(x ->
        queryBuilder.append("FILTER(xs:dateTime(?dateCreated) >= xs:dateTime(\"").append(x.toXMLFormat()).append("\"))\n "));

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getDateCreatedToTimestamp)
      .map(XMLUtil::toXmlCalendar).ifPresent(x ->
      queryBuilder.append("FILTER(xs:dateTime(?dateCreated) <= xs:dateTime(\"").append(x.toXMLFormat()).append("\")) \n"));

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

    Optional.ofNullable(aktMetadataPredicate)
      .map(AktMetadataPredicate::getAktState)
      .ifPresent(x -> sparqlQueryDefinition.withBinding("searchStanje", aktMetadataPredicate.getAktState()));


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
        akt.setState(node.get("stanje").path("value").asText());
        akt.setVersion(node.get("verzija").path("value").asText());

        //this is needed when client queries for only his documents, than marklogic does not return 'user' in result
        String author = Optional.ofNullable(node.get("user"))
          .map(p -> p.path("value"))
          .map(JsonNode::asText)
          .map(s -> s.split("/"))
          .map(g -> g[g.length - 1])
          .orElse(SecurityUtils.getCurrentUserLogin());
        akt.setAuthor(author);
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
  public AktMetadata getMetadata(String id) {
    throw new NotImplementedException();
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

    if (aktDTO.getResult().equals("accepted")) {
      rdfService.updateTripleAkt(id, AktStates.IZGLASAN, STANJE, AKT_GRAPH_URI);
    } else if (aktDTO.getResult().equals("declined")) {
      rdfService.updateTripleAkt(id, AktStates.ODBIJEN, STANJE, AKT_GRAPH_URI);
    }

    Akt aktDb = findById(akt.getId());

    if (aktDTO.getForVote() != null) {
      xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@for", aktDb.getDocumentAktRef().getDocument().getResults().getFor() + 1).build();
      documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);
    } else if (aktDTO.getAgainst() != null) {
      xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@against", aktDb.getDocumentAktRef().getDocument().getResults().getAgainst() + 1).build();
      documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);
    }

    xmlPatch = builder.replaceValue("//akt:akt/akt:document_akt_ref/document:document/document:results/@notVote", aktDTO.getNotVote()).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, akt.getId()), xmlPatch);

    return aktDTO;
  }

  private Akt refreshDocValues(Akt akt) {
    akt.getDocumentAktRef().getDocument().setState("default");
    akt.getDocumentAktRef().getDocument().setResult("default");
    akt.getDocumentAktRef().getDocument().getResults().setAgainst(0);
    akt.getDocumentAktRef().getDocument().getResults().setFor(0);
    akt.getDocumentAktRef().getDocument().getResults().setNotVote(0);
    return akt;
  }

  public AktDTO mergeAkt(Akt akt, ArrayList<Amandman> amandmans) throws JAXBException {
    akt.getOtherAttributes().clear();
    String oldAktId = akt.getId();
    akt = refreshDocValues(akt);
    akt = add(akt);
    for (Amandman am : amandmans) {
      for (Izmena izm : am.getIzmene().getIzmena()) {
        if (izm.getPredmetIzmene().getTipIzmene().equals(TTipIzmene.IZMENA)) {
          for (Resenje res : izm.getResenja().getResenje()) {
            if (izm.getPredmetIzmene().getRefClanovi() != null) {
              patchAkt(akt.getId(), res.getClan(), izm.getPredmetIzmene().getRefClanovi(), Clan.class, "clan");
            } else if (izm.getPredmetIzmene().getRefTacke() != null) {
              patchAkt(akt.getId(), res.getTacka(), izm.getPredmetIzmene().getRefTacke(), Tacka.class, "tacka");
            } else if (izm.getPredmetIzmene().getRefAlineje() != null) {
              patchAkt(akt.getId(), res.getAlineja(), izm.getPredmetIzmene().getRefAlineje(), Alineja.class, "alineja");
            } else if (izm.getPredmetIzmene().getRefPodtacke() != null) {
              patchAkt(akt.getId(), res.getPodtacka(), izm.getPredmetIzmene().getRefPodtacke(), Podtacka.class, "podtacka");
            } else if (izm.getPredmetIzmene().getRefStavovi() != null) {
              patchAkt(akt.getId(), res.getStav(), izm.getPredmetIzmene().getRefStavovi(), Stav.class, "stavovi");
            }
          }
        } else if (izm.getPredmetIzmene().getTipIzmene().equals(TTipIzmene.BRISANJE)) {
          //for(Resenje res : izm.getResenja().getResenje()){
          if (izm.getPredmetIzmene().getRefClanovi() != null) {
            deleteElement(akt.getId(), izm.getPredmetIzmene().getRefClanovi(), "clan");
          } else if (izm.getPredmetIzmene().getRefTacke() != null) {
            deleteElement(akt.getId(), izm.getPredmetIzmene().getRefTacke(), "tacka");
          } else if (izm.getPredmetIzmene().getRefAlineje() != null) {
            deleteElement(akt.getId(), izm.getPredmetIzmene().getRefAlineje(), "alineja");
          } else if (izm.getPredmetIzmene().getRefPodtacke() != null) {
            deleteElement(akt.getId(), izm.getPredmetIzmene().getRefPodtacke(), "podtacka");
          } else if (izm.getPredmetIzmene().getRefStavovi() != null) {
            deleteElement(akt.getId(), izm.getPredmetIzmene().getRefStavovi(), "stav");
          }
          //}
        } else if (izm.getPredmetIzmene().getTipIzmene().equals(TTipIzmene.DOPUNA)) {
//          for(Resenje res : izm.getResenja().getResenje()){
          if (izm.getPredmetIzmene().getRefClanovi() != null) {
            addElementOnAkt(akt.getId(), izm.getResenja().getResenje(), izm.getPredmetIzmene().getRefClanovi(), Clan.class, "clan");
          } else if (izm.getPredmetIzmene().getRefTacke() != null) {
            addElementOnAkt(akt.getId(), izm.getResenja().getResenje(), izm.getPredmetIzmene().getRefTacke(), Tacka.class, "tacka");
          } else if (izm.getPredmetIzmene().getRefAlineje() != null) {
            addElementOnAkt(akt.getId(), izm.getResenja().getResenje(), izm.getPredmetIzmene().getRefAlineje(), Alineja.class, "alineja");
          } else if (izm.getPredmetIzmene().getRefPodtacke() != null) {
            addElementOnAkt(akt.getId(), izm.getResenja().getResenje(), izm.getPredmetIzmene().getRefPodtacke(), Podtacka.class, "podtacka");
          } else if (izm.getPredmetIzmene().getRefStavovi() != null) {
            addElementOnAkt(akt.getId(), izm.getResenja().getResenje(), izm.getPredmetIzmene().getRefStavovi(), Stav.class, "stav");
          }
//          }
        }
      }
    }


    akt = findById(akt.getId());

    rdfService.updateTripleAkt(oldAktId, AktStates.STARI, VERZIJA, AKT_GRAPH_URI);

    AktDTO aktDTO = new AktDTO();
    aktDTO.setId(akt.getId());
    aktDTO.setName(akt.getZaglavlje().getNaziv().getValue());
    return aktDTO;
  }

  private void addElementOnAkt(String aktId, List<Resenje> resenja, String id, Class myClass, String path) throws JAXBException {
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("akt", AKT);
    namespaces.put("meta", META);
    namespaces.put("document", DOCUMENT);
    DocumentPatchBuilder builder;// = documentManager.newPatchBuilder();
    Object obj = null;
    String new_id = null;
    DocumentPatchHandle xmlPatch = null;
    for (Resenje res : resenja) {
      builder = documentManager.newPatchBuilder();
      builder.setNamespaces(namespaces);
      if (myClass == Clan.class) {
        obj = res.getClan();
        new_id = res.getClan().getId();
      } else if (myClass == Tacka.class) {
        obj = res.getTacka();
        new_id = res.getTacka().getId();
      } else if (myClass == Alineja.class) {
        obj = res.getAlineja();
        new_id = res.getAlineja().getId();
      } else if (myClass == Podtacka.class) {
        obj = res.getPodtacka();
        new_id = res.getPodtacka().getId();
      } else if (myClass == Stav.class) {
        obj = res.getStav();
        new_id = res.getStav().getId();
      }
      xmlPatch = builder.insertFragment("//akt:" + path + "[@meta:id=\"" + id + "\"]", DocumentPatchBuilder.Position.AFTER, toXmlString(obj, myClass)).build();
      documentManager.patch(getDocumentId(AKT_FORMAT, aktId), xmlPatch);
      id = new_id;
    }
  }

  private void deleteElement(String aktId, String id, String path) {
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("akt", AKT);
    namespaces.put("meta", META);
    namespaces.put("document", DOCUMENT);
    DocumentPatchBuilder builder = documentManager.newPatchBuilder();
    builder.setNamespaces(namespaces);
    DocumentPatchHandle xmlPatch = builder.delete("//akt:" + path + "[@meta:id=\"" + id + "\"]").build();
    documentManager.patch(getDocumentId(AKT_FORMAT, aktId), xmlPatch);
  }

  private void patchAkt(String aktId, Object obj, String id, Class myClass, String path) throws JAXBException {
    EditableNamespaceContext namespaces = new EditableNamespaceContext();
    namespaces.put("akt", AKT);
    namespaces.put("meta", META);
    namespaces.put("document", DOCUMENT);
    DocumentPatchBuilder builder = documentManager.newPatchBuilder();
    builder.setNamespaces(namespaces);
    DocumentPatchHandle xmlPatch = builder.replaceFragment("//akt:" + path + "[@meta:id=\"" + id + "\"]", toXmlString(obj, myClass)).build();
    documentManager.patch(getDocumentId(AKT_FORMAT, aktId), xmlPatch);
  }

  private String toXmlString(Object object, Class myClass) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(myClass);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
    StringWriter writer = new StringWriter();
    marshaller.marshal(object, writer);

    return writer.toString();
  }
}
