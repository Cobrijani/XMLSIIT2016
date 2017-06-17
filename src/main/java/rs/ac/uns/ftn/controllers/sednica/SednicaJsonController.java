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
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanForSednicaDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaPostDTO;
import rs.ac.uns.ftn.model.generated.Informacije;
import rs.ac.uns.ftn.model.generated.Naziv;
import rs.ac.uns.ftn.model.generated.Sednica;
import rs.ac.uns.ftn.model.generated.ZaglavljeSednica;
import rs.ac.uns.ftn.model.metadata.SednicaMetadata;
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
  public ResponseEntity<SednicaDTO> getOne(@PathVariable String id) {
    Sednica sednica = sednicaService.findById(id);
    SednicaDTO sednicaDTO = createDTOFromSednica(sednica);
    return ResponseEntity.ok(sednicaDTO);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    sednicaService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> postSednica(@RequestBody SednicaPostDTO sednicaDTO, UriComponentsBuilder builder) {
    Sednica sednica = createSednicaFromDTO(sednicaDTO);
    sednicaService.add(sednica, sednicaDTO.getAkti(), sednicaDTO.getAmandmani());

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
      builder.path(properties.getAktProperties().getActUri())
        .buildAndExpand(sednicaDTO.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> putSednica(@RequestBody SednicaDTO sednicaDTO, @PathVariable String id) {
    sednicaService.putSednica(id, sednicaDTO);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/{id}/akti")
  public ResponseEntity<List<PutAktDTO>> getSednicaAkts(@PathVariable String id) {
    List<PutAktDTO> akts = sednicaService.findSednicaAktsById(id);
    return ResponseEntity.ok(akts);
  }

  @GetMapping(value = "/{id}/amandmani")
  public ResponseEntity<List<AmandmanForSednicaDTO>> getSednicaAmandmands(@PathVariable String id) {
    List<AmandmanForSednicaDTO> amandmans = sednicaService.findSednicaAmandmandsById(id);
    return ResponseEntity.ok(amandmans);
  }

  private SednicaDTO createDTOFromSednica(Sednica sednica) {
    SednicaDTO sednicaDTO = new SednicaDTO();
    sednicaDTO.setId(sednica.getId());
    sednicaDTO.setNaziv(sednica.getZaglavljeSednica().getNaziv().getValue());
    sednicaDTO.setMesto(sednica.getInformacije().getMesto());
    sednicaDTO.setDatum(sednica.getInformacije().getDatum());
    sednicaDTO.setZavrsena(sednica.getInformacije().isZavrsena());
    return sednicaDTO;
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

