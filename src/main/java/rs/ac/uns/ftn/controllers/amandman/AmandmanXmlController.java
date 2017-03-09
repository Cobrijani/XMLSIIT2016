package rs.ac.uns.ftn.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;
import rs.ac.uns.ftn.services.AmandmanService;

/**
 * Created by SBratic on 3/7/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/amandmani", produces = MediaType.APPLICATION_XML_VALUE)
public class AmandmanXmlController {

  private final AmandmanService amandmanService;

  private final XMLSIITProperties properties;


  public AmandmanXmlController(AmandmanService amandmanService, XMLSIITProperties properties) {
    this.amandmanService = amandmanService;
    this.properties = properties;
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createAmandman(@RequestBody Amandman amandman, UriComponentsBuilder builder) {
    amandmanService.add(amandman);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAmandmanProperties().getAmandmanUri())
        .buildAndExpand(amandman.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}
