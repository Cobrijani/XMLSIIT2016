package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.model.korisnici.Korisnik;

import java.util.List;

/**
 * Created by SBratic on 1/18/2017.
 */
public interface KorisnikService {


  List<Korisnik> findAll();

  List<Korisnik> findAll(Pageable pageable);

  Korisnik findById(String id);

  Korisnik findByUsername(String username);

  Korisnik saveKorisnik(Korisnik korisnik);

  void deleteKorisnikById(String id);
}
