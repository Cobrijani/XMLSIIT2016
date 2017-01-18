package rs.ac.uns.ftn.util;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods for working with JAXB
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@UtilityClass
public class XMLUtil {

  /**
   * Creates {@link JAXBHandle} for given class
   *
   * @param clazz class to be entered
   * @return instance of new {@link JAXBHandle}
   */
  public static <T> JAXBHandle<T> getJaxbHandle(Class<T> clazz) {
    try {
      JAXBContext context = JAXBContext.newInstance(clazz);
      return new JAXBHandle<>(context);
    } catch (JAXBException ex) {
      log.error(ex.toString());
      throw new RuntimeException(ex.toString());
    }
  }

  public static String getDocumentId(String format, String id) {
    return String.format(format, id);
  }

  public static <T> List<T> convertSearchHandle(SearchHandle searchHandle, XMLDocumentManager xmlDocumentManager, Class<T> clazz) {
    List<T> retVal = new ArrayList<T>();

    for (MatchDocumentSummary summary :
      searchHandle.getMatchResults()) {

      JAXBHandle<T> handle = getJaxbHandle(clazz);
      xmlDocumentManager.read(summary.getUri(), handle);
      retVal.add(handle.get());
    }

    return retVal;
  }
}
