package rs.ac.uns.ftn.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.model.AktStates;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stefan Bratić <cobrijani@gmail.com>
 *         Created on 6/18/2017.
 */
@RestController()
@RequestMapping(value = "/api/v1/aktStates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AktStateController {


  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<String> GetAllStates() {
    return Arrays.asList(AktStates.IZGLASAN,
      AktStates.NOV,
      AktStates.ODBIJEN,
      AktStates.POVUCEN,
      AktStates.RAZMATRAN,
      AktStates.STARI);
  }
}
