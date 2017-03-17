package rs.ac.uns.ftn.controllers.sednica;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaPostDTO;
import rs.ac.uns.ftn.model.generated.Informacije;
import rs.ac.uns.ftn.model.generated.Naziv;
import rs.ac.uns.ftn.model.generated.Sednica;
import rs.ac.uns.ftn.model.generated.ZaglavljeSednica;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.SednicaService;
import rs.ac.uns.ftn.util.XMLUtil;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * Created by Micko on 03-Mar-17.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/sednice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SednicaJsonController {

  private final SednicaService sednicaService;

  private final XMLSIITProperties properties;

  private final ModelMapper modelMapper;

  @Autowired
  public SednicaJsonController(SednicaService sednicaService, XMLSIITProperties properties, ModelMapper modelMapper) {
    this.sednicaService = sednicaService;
    this.properties = properties;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<List<SednicaDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(sednicaService.getMetadata(pageable));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Sednica> getOne(@PathVariable String id) {
    Sednica sednica = sednicaService.findById(id);
    return ResponseEntity.ok(sednica);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    sednicaService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> postAkt(@RequestBody SednicaPostDTO sednicaDTO, UriComponentsBuilder builder) {
    Sednica sednica = createSednicaFromDTO(sednicaDTO);
    sednicaService.add(sednica, sednicaDTO.getAkti(), sednicaDTO.getAmandmani());

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(sednicaDTO.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  private Sednica createSednicaFromDTO(SednicaPostDTO sednicaDTO) {
    Sednica sednica = new Sednica();
    sednica.setZaglavljeSednica(new ZaglavljeSednica());
    sednica.getZaglavljeSednica().setNaziv(new Naziv());
    sednica.getZaglavljeSednica().getNaziv().setValue(sednicaDTO.getNaziv());
    sednica.setInformacije(new Informacije());
    sednica.getInformacije().setMesto(sednicaDTO.getMesto());
    sednica.getInformacije().setDatum(sednicaDTO.getDatum());
    return sednica;
  }
}

