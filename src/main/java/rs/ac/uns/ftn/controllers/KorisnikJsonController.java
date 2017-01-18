package rs.ac.uns.ftn.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.services.KorisnikService;

import java.util.List;

/**
 * Created by SBratic on 1/18/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/korisnici", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KorisnikJsonController {


  private final KorisnikService korisnikService;

  public KorisnikJsonController(KorisnikService korisnikService) {
    this.korisnikService = korisnikService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Korisnik> getKorisnici(Pageable pageable) {
    return korisnikService.findAll(pageable);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Korisnik getOne(@PathVariable(value = "id") String id) {
    return korisnikService.findById(id);
  }
}
