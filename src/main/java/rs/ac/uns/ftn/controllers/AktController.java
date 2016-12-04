package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.xml.Akt;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktXmlService;

/**
 * Controller that handles operations related to {@link rs.ac.uns.ftn.model.xml.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/aktovi")
public class AktController {


  private final AktXmlService aktXmlService;
  private final XMLSIITProperties properties;


  @Autowired
  public AktController(AktXmlService aktXmlService, XMLSIITProperties properties) {
    this.aktXmlService = aktXmlService;
    this.properties = properties;
  }

  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createAkt(@RequestBody Akt akt, UriComponentsBuilder builder) {
    aktXmlService.add(akt);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(akt.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}
