package rs.ac.uns.ftn.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.services.AktService;
import rs.ac.uns.ftn.services.AmandmanService;
import rs.ac.uns.ftn.util.XMLUtil;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by Arsa on 3/18/2017.
 */
@Controller
@RequestMapping(value = "/api/v1/amandmani", produces = MediaType.TEXT_HTML_VALUE)
public class AmandmanHtmlController {

  private final AmandmanService amandmanService;

  public AmandmanHtmlController(AmandmanService amandmanService) {
    this.amandmanService = amandmanService;
  }


  @GetMapping(value = "/{id}")
  public void getOne(@PathVariable String id, HttpServletResponse response) {
    Document document = amandmanService.findById(id, Document.class);
    try {
      XMLUtil.generateHtml(document, response.getOutputStream(), "xslt/xhtml/amandman-xhtml.xsl");
      response.setContentType(MediaType.TEXT_HTML_VALUE);
      response.flushBuffer();
    } catch (TransformerException | IOException e) {
      e.printStackTrace();
    }
  }
}
