package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;

import java.util.List;

/**
 * Controller that handles operations related to {@link rs.ac.uns.ftn.model.generated.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/aktovi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AktJsonController {


  private final AktService aktService;
  private final XMLSIITProperties properties;

  @Autowired
  public AktJsonController(AktService aktService, XMLSIITProperties properties) {
    this.aktService = aktService;
    this.properties = properties;
  }

  @GetMapping
  public ResponseEntity<List<Akt>> findAll(Pageable pageable) {
    List<Akt> akts = aktService.findAll(pageable);
    return ResponseEntity.ok(akts);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Akt> getOne(@PathVariable String id, Model model) {
    Akt akt = aktService.findById(id);
    return ResponseEntity.ok(akt);
  }

  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createAkt(@RequestBody Akt akt, UriComponentsBuilder builder) {
    aktService.add(akt);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(akt.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteAkt(@PathVariable String id) {
    aktService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
