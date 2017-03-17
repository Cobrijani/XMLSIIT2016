package rs.ac.uns.ftn.controllers.amandman;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.AmandmanService;

import java.util.List;

/**
 * Controller that handles operations related to {@link rs.ac.uns.ftn.model.generated.Akt}
 * Created by SBratic on 12/3/2016.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/amandmani", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AmandmanJsonController {


  private final AmandmanService amandmanService;

  private final XMLSIITProperties properties;

  private final ModelMapper modelMapper;

  @Autowired
  public AmandmanJsonController(AmandmanService amandmanService, XMLSIITProperties properties, ModelMapper modelMapper) {
    this.amandmanService = amandmanService;
    this.properties = properties;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<List<AmandmanMetadata>> findAll(Pageable pageable) {
    return ResponseEntity.ok(amandmanService.getMetadata(pageable));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Amandman> getOne(@PathVariable String id) {
    Amandman amandman = amandmanService.findById(id);
    return ResponseEntity.ok(amandman);
  }


  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteAkt(@PathVariable String id) {
    amandmanService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
