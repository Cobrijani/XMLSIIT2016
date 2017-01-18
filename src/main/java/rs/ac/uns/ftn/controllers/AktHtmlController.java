package rs.ac.uns.ftn.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.model.akt.Akt;
import rs.ac.uns.ftn.services.AktService;

/**
 * Created by SBratic on 1/18/2017.
 */
@Controller
@RequestMapping(value = "/api/v1/aktovi", produces = MediaType.TEXT_HTML_VALUE)
public class AktHtmlController {

  private final AktService aktService;

  public AktHtmlController(AktService aktService) {
    this.aktService = aktService;
  }


  @GetMapping()
  public String getAll(Pageable pageable, Model model) {
    model.addAttribute("aktovi", aktService.findAll(pageable));
    return "aktovi :: aktoviFragment";
  }

  @GetMapping(value = "/{id}")
  public String getOne(@PathVariable String id, Model model) {
    Akt akt = aktService.findById(id);
    model.addAttribute("akt", akt);

    return "akt :: aktFragment";
  }


}
