package rs.ac.uns.ftn.util;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

  public static void generateHtml(Document document, OutputStream outputStream, String resourceXslPath) throws TransformerException {
    /* Inicijalizacija DOM fabrike */
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setIgnoringComments(true);
    documentBuilderFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
    TransformerFactory transformerFactory = TransformerFactory.newInstance();

    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream xsltFile = classLoader.getResourceAsStream(resourceXslPath);

    StreamSource transformSource = new StreamSource(xsltFile);
    Transformer transformer = transformerFactory.newTransformer(transformSource);
    transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    // Generate XHTML
    transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

    // Transform DOM to HTML
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(outputStream);
    transformer.transform(source, result);
  }

}
