package rs.ac.uns.ftn.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;

/**
 * Created by SBratic on 1/18/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/akti", produces = MediaType.APPLICATION_XML_VALUE)
public class AktXmlController {

  private final AktService aktService;

  private final XMLSIITProperties properties;


  public AktXmlController(AktService aktService, XMLSIITProperties properties) {
    this.aktService = aktService;
    this.properties = properties;
  }

  @GetMapping
  public String findAll(Pageable pageable, AktMetadataPredicate predicate) {
    return aktService.getSparqlResult(pageable, predicate);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createAkt(@RequestBody Akt akt, UriComponentsBuilder builder) {
    aktService.add(akt);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(akt.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}
