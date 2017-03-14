package rs.ac.uns.ftn.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.model.ValidationResult;
import rs.ac.uns.ftn.services.ValidationService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SBratic on 3/13/2017.
 */
@RequestMapping("api/v1/util")
@RestController
public class UtilityController {


  private final ValidationService validationService;

  private final Map<String, Resource> schemaRegistry;

  @Value("classpath:schemas/akt.xsd")
  private Resource aktXsd;
  @Value("classpath:schemas/metadata.xsd")
  private Resource metaXsd;

  public UtilityController(ValidationService validationService) {
    this.validationService = validationService;
    schemaRegistry = new HashMap<>();
  }

  @PostConstruct
  public void postConstruct() {
    schemaRegistry.put("akt", aktXsd);
    schemaRegistry.put("meta", metaXsd);
  }

  @PostMapping(path = "/validate")
  public ResponseEntity<ValidationResult> validateContent(@RequestBody String xmlContent, @RequestParam(name = "xsd") String[] xsdName) {
    Resource[] res = Arrays.stream(xsdName)
      .map(schemaRegistry::get)
      .toArray(Resource[]::new);
    ValidationResult validationResult = validationService.validate(xmlContent, res);
    return ResponseEntity.ok(validationResult);
  }
}
