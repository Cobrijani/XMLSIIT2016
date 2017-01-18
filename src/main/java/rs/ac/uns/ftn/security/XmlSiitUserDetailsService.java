package rs.ac.uns.ftn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.security.model.KorisnikUserDetails;
import rs.ac.uns.ftn.services.KorisnikService;

import java.util.Optional;

/**
 * Created by SBratic on 10/27/2016.
 */
@Service
public class XmlSiitUserDetailsService implements UserDetailsService {

  private final KorisnikService korisnikService;

  @Autowired
  public XmlSiitUserDetailsService(KorisnikService korisnikService) {
    this.korisnikService = korisnikService;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    Optional<Korisnik> user = Optional.ofNullable(korisnikService.findByUsername(s));

    return user
      .map(KorisnikUserDetails::new)
      .orElseThrow(() ->
        new UsernameNotFoundException(String.format("User with username %s was not found", s)));
  }
}
