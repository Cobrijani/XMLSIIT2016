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
import rs.ac.uns.ftn.dto.akt.GenerateAktDTO;
import rs.ac.uns.ftn.dto.akt.MergeAktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;
import rs.ac.uns.ftn.services.AmandmanService;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
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

  private final AmandmanService amandmanService;

  private final XMLSIITProperties properties;

  private final ModelMapper modelMapper;

  @Autowired
  public AktJsonController(AktService aktService, AmandmanService amandmanService, XMLSIITProperties properties, ModelMapper modelMapper) {
    this.aktService = aktService;
    this.amandmanService = amandmanService;
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
  @ResponseStatus(HttpStatus.OK)
  public void deleteAkt(@PathVariable String id) {
    aktService.deleteAktById(id);
  }

  @GetMapping(value = "/{id}/amandmani")
  public ResponseEntity<List<AmandmanMetadata>> getAktsAmandmands(@PathVariable String id) {
    List<AmandmanMetadata> amandmands = aktService.findAktAmandmandsById(id);
    return ResponseEntity.ok(amandmands);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PutAktDTO> putAkt(@RequestBody PutAktDTO aktDTO, @PathVariable String id) {
    PutAktDTO akt = aktService.putAkt(id, aktDTO);
    return ResponseEntity.ok(akt);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AktDTO>> mergeAkt(@RequestBody GenerateAktDTO generateAktDTO) throws JAXBException {
    ArrayList<AktDTO> akts = new ArrayList<>();
    for (MergeAktDTO mergeAkt : generateAktDTO.getAkts()) {
      Akt akt = aktService.findById(mergeAkt.getAktId());
      ArrayList<Amandman> amandmans = new ArrayList<Amandman>();
      for (String amId : mergeAkt.getAmandmanIds()) {
        Amandman am = amandmanService.findById(amId);
        amandmans.add(am);
      }
      AktDTO aktDTO = aktService.mergeAkt(akt, amandmans);
      akts.add(aktDTO);
    }


    return new ResponseEntity<>(akts, HttpStatus.CREATED);
  }

}
