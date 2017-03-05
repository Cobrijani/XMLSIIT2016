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
import rs.ac.uns.ftn.security.SecurityUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.GregorianCalendar;
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

  @Autowired
  public AktMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator, RdfService rdfService) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
    this.rdfService = rdfService;
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
    akt.setDateCreated(new XMLGregorianCalendarImpl(new GregorianCalendar()));
    akt.setDateModified(new XMLGregorianCalendarImpl(new GregorianCalendar()));

    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle<Akt> handle = getJaxbHandle(Akt.class);
    handle.set(akt);
    documentManager.write(getDocumentId(AKT_FORMAT, akt.getId()), documentMetadataHandle, handle);

    Document document = findById(id, Document.class);

    rdfService.extractAndWriteData(document, AKT_GRAPH_URI);
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
}
