package rs.ac.uns.ftn.controllers.sednica;

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
import rs.ac.uns.ftn.model.generated.sednica.Sednica;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.SednicaService;

/**
 * Created by Micko on 04-Mar-17.
 */
@RestController
@RequestMapping(value = "/api/v1/sednice", produces = MediaType.APPLICATION_XML_VALUE)
public class SednicaXmlController {

  private final SednicaService sednicaService;

  private final XMLSIITProperties properties;


  public SednicaXmlController(SednicaService sednicaService, XMLSIITProperties properties) {
    this.sednicaService = sednicaService;
    this.properties = properties;
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createAkt(@RequestBody Sednica sednica, UriComponentsBuilder builder) {
    sednicaService.add(sednica);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(sednica.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}

