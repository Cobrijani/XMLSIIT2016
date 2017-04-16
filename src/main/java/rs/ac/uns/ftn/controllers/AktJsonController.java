package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanDTO;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;

import java.util.List;

/**
 * Controller that handles operations related to {@link rs.ac.uns.ftn.model.generated.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/akti", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AktJsonController {


  private final AktService aktService;

  private final XMLSIITProperties properties;

  private final ModelMapper modelMapper;

  @Autowired
  public AktJsonController(AktService aktService, XMLSIITProperties properties, ModelMapper modelMapper) {
    this.aktService = aktService;
    this.properties = properties;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<Page<AktMetadata>> findAll(Pageable pageable, AktMetadataPredicate predicate) {
    return ResponseEntity.ok(aktService.getMetadataPage(pageable, predicate));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Akt> getOne(@PathVariable String id) {
    Akt akt = aktService.findById(id);
    return ResponseEntity.ok(akt);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteAkt(@PathVariable String id) {
    aktService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/amandmani")
  public ResponseEntity<List<AmandmanMetadata>> getAktsAmandmands(@PathVariable String id) {
    List<AmandmanMetadata> amandmands = aktService.findAktAmandmandsById(id);
    return ResponseEntity.ok(amandmands);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PutAktDTO> putAkt(@RequestBody PutAktDTO aktDTO, @PathVariable String id){
    PutAktDTO akt = aktService.putAkt(id, aktDTO);
    return ResponseEntity.ok(akt);
  }
}
