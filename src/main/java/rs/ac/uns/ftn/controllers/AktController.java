package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.akt.Akt;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktXmlService;

/**
 * Controller that handles operations related to {@link rs.ac.uns.ftn.model.akt.Akt}
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

  @GetMapping
  public ResponseEntity<Page<Akt>> findAll(Pageable pageable) {
    Page<Akt> akts = aktXmlService.findAll(pageable);
    return new ResponseEntity<>(akts, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Akt> getOne(@PathVariable String id) {
    Akt akt = aktXmlService.findById(id);
    return new ResponseEntity<>(akt, HttpStatus.OK);
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

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteAkt(@PathVariable String id) {
    aktXmlService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
