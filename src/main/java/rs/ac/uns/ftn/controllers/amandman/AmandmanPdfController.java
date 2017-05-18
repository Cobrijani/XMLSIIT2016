package rs.ac.uns.ftn.controllers.amandman;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import rs.ac.uns.ftn.services.AmandmanService;
import rs.ac.uns.ftn.util.XMLUtil;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by SBratic on 3/2/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/amandmani", produces = MediaType.APPLICATION_PDF_VALUE)
public class AmandmanPdfController {

  private final AmandmanService amandmanService;

  public AmandmanPdfController(AmandmanService amandmanService) {
    this.amandmanService = amandmanService;
  }


  @GetMapping(value = "/{id}")
  public void getOne(@PathVariable String id, HttpServletResponse response) {
    Document document = amandmanService.findById(id, Document.class);
    try {
      XMLUtil.toPdf(document, response.getOutputStream(), "src/main/resources/xslt/pdf/amandman-pdf.xsl");
      response.setContentType(MediaType.APPLICATION_PDF_VALUE);
      response.flushBuffer();
    } catch (TransformerException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }




}
