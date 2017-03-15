package rs.ac.uns.ftn.controllers;

import javaslang.control.Try;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.exceptions.SchemaNotFoundException;
import rs.ac.uns.ftn.model.ValidationResult;
import rs.ac.uns.ftn.services.Registry;
import rs.ac.uns.ftn.services.ValidationService;

import java.util.Arrays;

/**
 * Created by SBratic on 3/13/2017.
 */
@RequestMapping("api/v1/util")
@RestController
public class UtilityController {


  private final ValidationService validationService;

  private final Registry<String, Resource> registry;


  public UtilityController(ValidationService validationService, Registry<String, Resource> registry) {
    this.validationService = validationService;
    this.registry = registry;
  }

  @PostMapping(path = "/validate")
  public ResponseEntity<ValidationResult> validateContent(@RequestBody String xmlContent, @RequestParam(name = "xsd") String[] xsdName) {
    Resource[] res = Try.of(() -> Arrays.stream(xsdName)
      .map(registry::getItemFromRegistry)
      .toArray(Resource[]::new)).getOrElseThrow(x -> new SchemaNotFoundException());
    ValidationResult validationResult = validationService.validate(xmlContent, res);
    return ResponseEntity.ok(validationResult);
  }
}
