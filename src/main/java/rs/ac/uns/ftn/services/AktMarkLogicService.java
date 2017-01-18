package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.akt.Akt;

import java.util.List;

import static rs.ac.uns.ftn.util.XMLUtil.*;

/**
 * Service for handling XML documents for {@link rs.ac.uns.ftn.model.akt.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Service
public class AktMarkLogicService implements AktService {

  private static final String AKT_REF = "/akt.xml";

  private static final String AKT_FORMAT = "/aktovi/%s.xml";

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private final IdentifierGenerator identifierGenerator;


  @Autowired
  public AktMarkLogicService(XMLDocumentManager documentManager, QueryManager queryManager, IdentifierGenerator identifierGenerator) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
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
    akt.setId(identifierGenerator.generateIdentity());
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle<Akt> handle = getJaxbHandle(Akt.class);
    handle.set(akt);
    documentManager.write(getDocumentId(AKT_FORMAT, akt.getId()), documentMetadataHandle, handle);
  }
}
