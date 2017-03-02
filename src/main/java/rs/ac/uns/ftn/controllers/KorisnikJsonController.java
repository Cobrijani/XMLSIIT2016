package rs.ac.uns.ftn.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.dto.converters.korisnik.RegisterKorisnikDtoConverter;
import rs.ac.uns.ftn.dto.objects.RegisterDTO;
import rs.ac.uns.ftn.dto.objects.korisnik.KorisnikDTO;
import rs.ac.uns.ftn.exceptions.KorisnikNotFoundException;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.services.KorisnikMarkLogicService;

import java.util.List;
import java.util.Optional;

/**
 * Created by SBratic on 1/18/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/korisnici", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KorisnikJsonController {


  private final KorisnikMarkLogicService korisnikService;
  private final RegisterKorisnikDtoConverter registerKorisnikDtoConverter;
  private final ModelMapper modelMapper;

  @Autowired
  public KorisnikJsonController(KorisnikMarkLogicService korisnikService,
                                ModelMapper modelMapper,
                                @Qualifier("RegisterKorisnikDtoConverter")
                                  RegisterKorisnikDtoConverter registerKorisnikDtoConverter
  ) {
    this.korisnikService = korisnikService;
    this.modelMapper = modelMapper;
    this.registerKorisnikDtoConverter = registerKorisnikDtoConverter;

  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Korisnik> getKorisnici(Pageable pageable) {
    return korisnikService.findAll(pageable);
  }

  @GetMapping(value = "/{username}")
  @ResponseStatus(HttpStatus.OK)
  public KorisnikDTO getOne(@PathVariable(value = "username") String username) {
    return Optional.ofNullable(korisnikService.findByUsername(username))
      .map(this::convertToDto)
      .orElseThrow(KorisnikNotFoundException::new);
  }


  /**
   * Add new {@link User}
   *
   * @param registerDTO data
   * @return new instance of {@link ResponseEntity}
   */
  @PostMapping(consumes = "application/json")
  public ResponseEntity<KorisnikDTO> registerKorisnik(@RequestBody RegisterDTO registerDTO) {
    Korisnik korisnik = registerKorisnikDtoConverter.convert(registerDTO, new Korisnik());

    korisnik = korisnikService.saveKorisnik(korisnik);

    return new ResponseEntity<>(convertToDto(korisnik), HttpStatus.CREATED);
  }

  private KorisnikDTO convertToDto(Korisnik korisnik) {
    return modelMapper.map(korisnik, KorisnikDTO.class);
  }
}
