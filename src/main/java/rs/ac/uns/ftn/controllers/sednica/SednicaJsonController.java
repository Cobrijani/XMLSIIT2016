package rs.ac.uns.ftn.controllers.sednica;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.model.generated.sednica.Sednica;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.SednicaService;

import java.util.List;
import java.util.stream.Collectors;

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
    List<SednicaDTO> akts =
      sednicaService.findAll(pageable).stream().map(x -> modelMapper.map(x, SednicaDTO.class)).collect(Collectors.toList());
    return ResponseEntity.ok(akts);
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
}

