package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AktService;

import java.util.List;
import java.util.stream.Collectors;

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

  private final ModelMapper modelMapper;

  @Autowired
  public AktJsonController(AktService aktService, XMLSIITProperties properties, ModelMapper modelMapper) {
    this.aktService = aktService;
    this.properties = properties;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<List<AktDTO>> findAll(Pageable pageable) {
    List<AktDTO> akts =
      aktService.findAll(pageable).stream().map(x -> modelMapper.map(x, AktDTO.class)).collect(Collectors.toList());
    return ResponseEntity.ok(akts);
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
}
