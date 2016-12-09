package rs.ac.uns.ftn.util;

import com.marklogic.client.io.JAXBHandle;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Helper methods for working with JAXB
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@UtilityClass
public class XMLUtil {

  /**
   * Creataes {@link JAXBHandle} for given class
   *
   * @param clazz class to be entered
   * @return instance of new {@link JAXBHandle}
   */
  public static JAXBHandle getJaxbHandle(Class clazz) {
    try {
      JAXBContext context = JAXBContext.newInstance(clazz);
      return new JAXBHandle(context);
    } catch (JAXBException ex) {
      log.error(ex.toString());
      throw new RuntimeException(ex.toString());
    }
  }

  public static String getDocumentId(String format, String id) {
    return String.format(format, id);
  }
}
