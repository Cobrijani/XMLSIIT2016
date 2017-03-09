package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.security.SecurityUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static rs.ac.uns.ftn.constants.XmlNamespaces.*;
import static rs.ac.uns.ftn.constants.XmlSiitGraphNames.AKT_GRAPH_URI;
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

  @Autowired
  public AmandmanMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator, RdfService rdfService) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
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
  public void add(Amandman amandman) {
    final String id = identifierGenerator.generateIdentity();
    amandman.setId(id);
    /*amandman.getOtherAttributes().put(new QName("about"), AMANDMAN + "/" + id);
    amandman.getOtherAttributes().put(new QName("vocab"), PRED);
    amandman.getOtherAttributes().put(new QName("typeof"), PRED_PREF + ":korisnik");
    amandman.getOtherAttributes().put(new QName("rel"), PRED_PREF + ":napravio");
    amandman.getOtherAttributes().put(new QName("href"), KORISNIK + "/" + SecurityUtils.getCurrentUserLogin());*/
    amandman.setDateCreated(new XMLGregorianCalendarImpl(new GregorianCalendar()));
    amandman.setDateModified(new XMLGregorianCalendarImpl(new GregorianCalendar()));

    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AMANDMAN_REF);

    JAXBHandle<Amandman> handle = getJaxbHandle(Amandman.class);
    handle.set(amandman);
    documentManager.write(getDocumentId(AMANDMAN_FORMAT, amandman.getId()), documentMetadataHandle, handle);

    Document document = findById(id, Document.class);

    rdfService.extractAndWriteData(document, AMANDMAN_GRAPH_URI);
  }

  @Override
  public void deleteAktById(String id) {
    throw new NotImplementedException();
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
}
