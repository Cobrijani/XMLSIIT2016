package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.generated.sednica.Sednica;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

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


  @Autowired
  public SednicaMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
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
    sednica.setId(identifierGenerator.generateIdentity());
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(SEDNICA_REF);

    JAXBHandle<Sednica> handle = getJaxbHandle(Sednica.class);
    handle.set(sednica);
    documentManager.write(getDocumentId(SEDNICA_FORMAT, sednica.getId()), documentMetadataHandle, handle);
  }

  @Override
  public void deleteAktById(String id) {
    throw new NotImplementedException();
  }

  @Override
  public void deleteAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(SEDNICA_REF);
    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    Arrays.stream(searchHandle.getMatchResults()).map(MatchDocumentSummary::getUri).forEach(documentManager::delete);
    log.info("Deleted all akts");
  }
}
