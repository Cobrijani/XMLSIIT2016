package rs.ac.uns.ftn.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.InputStreamResource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.ServletOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
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

  public static void generatePdf(Document document, OutputStream stream, String resourceXslPath) throws IOException, TransformerException, DocumentException {


    Font font = FontFactory.getFont("resources/fonts/Arial-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

    TransformerFactory factory = TransformerFactory.newInstance();

    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream xsltFile = classLoader.getResourceAsStream(resourceXslPath);
    Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
    transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

    ByteArrayOutputStream out = new ByteArrayOutputStream();


    transformer.transform(new DOMSource(document), new StreamResult(out));

    com.itextpdf.text.Document doc = new com.itextpdf.text.Document();


    PdfWriter writer = PdfWriter.getInstance(doc, stream);

    doc.open();

    log.info("Content: {}", out.toString("UTF-8"));

    final InputStream in = new ByteArrayInputStream(out.toString().getBytes(StandardCharsets.UTF_8));

    XMLWorkerHelper.getInstance().parseXHtml(writer, doc, in, StandardCharsets.UTF_8);

    doc.close();
    in.close();
    out.close();

  }


  public void toPdf(Document document, OutputStream outputStream, String xslFile) throws IOException, SAXException, TransformerException {
    final FopFactory fopFactory = FopFactory.newInstance(new File("src/main/resources/conf/fop.xconf"));

    final TransformerFactory transformerFactory = TransformerFactory.newInstance();

    InputStream xsltFile = new FileInputStream(xslFile);

    final DOMSource source = new DOMSource(document);
    final StreamSource xslSource = new StreamSource(xslFile);

    final FOUserAgent userAgent = fopFactory.newFOUserAgent();


    final Transformer xslFoTransformer = transformerFactory.newTransformer(xslSource);
    final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outputStream);
    final Result result = new SAXResult(fop.getDefaultHandler());


    xslFoTransformer.transform(source, result);

  }


}
