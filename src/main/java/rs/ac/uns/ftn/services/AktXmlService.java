package rs.ac.uns.ftn.services;

import com.marklogic.client.Page;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.xml.Akt;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Service for handling XML documents for {@link rs.ac.uns.ftn.model.xml.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Service("aktXmlService")
public class AktXmlService {

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  @Autowired
  public AktXmlService(XMLDocumentManager documentManager, QueryManager queryManager) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
  }


  public Akt findById(String id) {
    throw new NotImplementedException();
  }

  public void removeById(String id) {
    throw new NotImplementedException();
  }

  public Page<Akt> findAll(Pageable pageable) {
    queryManager.setPageLength(pageable.getPageSize());

    throw new NotImplementedException();
  }

  public Akt add(Akt akt) {
    throw new NotImplementedException();
  }


}
