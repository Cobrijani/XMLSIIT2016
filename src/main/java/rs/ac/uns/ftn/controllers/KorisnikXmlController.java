package rs.ac.uns.ftn.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.KorisnikService;

/**
 * {@link Korisnik} for XML media type
 * Created by SBratic on 1/18/2017.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE, value = "/api/v1/korisnici")
public class KorisnikXmlController {


  private final KorisnikService korisnikService;

  private final XMLSIITProperties properties;

  public KorisnikXmlController(KorisnikService korisnikService, XMLSIITProperties properties) {
    this.korisnikService = korisnikService;
    this.properties = properties;
  }


  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Void> createKorisnik(@RequestBody Korisnik korisnik, UriComponentsBuilder builder) {
    korisnikService.saveKorisnik(korisnik);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(korisnik.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
}
