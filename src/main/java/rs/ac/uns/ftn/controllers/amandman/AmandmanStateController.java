package rs.ac.uns.ftn.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.model.AmandmanStates;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Arsa on 6/21/2017..
 */
@RestController()
@RequestMapping(value = "/api/v1/amandmanStates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AmandmanStateController {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<String> GetAllStates() {
    return Arrays.asList(AmandmanStates.IZGLASAN,
      AmandmanStates.NOV,
      AmandmanStates.ODBIJEN,
      AmandmanStates.POVUCEN,
      AmandmanStates.RAZMATRAN,
      AmandmanStates.STARI);
  }
}
