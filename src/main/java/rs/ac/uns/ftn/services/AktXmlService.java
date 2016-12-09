package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.xml.Akt;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static rs.ac.uns.ftn.util.XMLUtil.getDocumentId;
import static rs.ac.uns.ftn.util.XMLUtil.getJaxbHandle;

/**
 * Service for handling XML documents for {@link rs.ac.uns.ftn.model.xml.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Service
//@Component
public class AktXmlService {

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private final String AKT_REF = "/akt.xml";

  private final String AKT_FORMAT = "/aktovi/%s.xml";


  @Autowired
  public AktXmlService(XMLDocumentManager documentManager, QueryManager queryManager) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
  }





  public Akt findById(String id){
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle<Akt> handle = getJaxbHandle(Akt.class);
    documentManager.read(getDocumentId(AKT_FORMAT, id), documentMetadataHandle, handle);

    Akt akt = handle.get();
    return akt;
  }

  public void removeById(String id) {
    documentManager.delete(getDocumentId(AKT_FORMAT, id));
  }

  public Page<Akt> findAll(Pageable pageable) {
    queryManager.setPageLength(pageable.getPageSize());

    throw new NotImplementedException();
  }

  public void add(Akt akt) {
    akt.setId("a");
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(AKT_REF);

    JAXBHandle handle = getJaxbHandle(Akt.class);
    handle.set(akt);
    documentManager.write(getDocumentId(AKT_FORMAT, akt.getId()), documentMetadataHandle, handle);
  }


}
