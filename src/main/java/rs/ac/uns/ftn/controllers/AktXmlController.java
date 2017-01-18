package rs.ac.uns.ftn.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.services.AktService;

/**
 * Created by SBratic on 1/18/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/aktovi", produces = MediaType.APPLICATION_XML_VALUE)
public class AktXmlController {

  private final AktService aktService;


  public AktXmlController(AktService aktService) {
    this.aktService = aktService;
  }
}
