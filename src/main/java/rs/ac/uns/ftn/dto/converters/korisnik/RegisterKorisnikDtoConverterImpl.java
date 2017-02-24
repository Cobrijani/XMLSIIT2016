package rs.ac.uns.ftn.dto.converters.korisnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.dto.objects.RegisterDTO;
import rs.ac.uns.ftn.exceptions.InvalidConfirmPasswordException;
import rs.ac.uns.ftn.exceptions.InvalidUlogaException;
import rs.ac.uns.ftn.model.korisnici.KorisnickiDetalji;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.model.korisnici.Uloga;

/**
 * Created by Arsa on 15-Feb-17.
 */
@Component("RegisterKorisnikDtoConverter")
public class RegisterKorisnikDtoConverterImpl implements RegisterKorisnikDtoConverter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegisterKorisnikDtoConverterImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public <T2 extends Korisnik, D2 extends RegisterDTO> T2 convert(D2 dto, T2 entity) {
    KorisnickiDetalji korisnickiDetalji = new KorisnickiDetalji();
    korisnickiDetalji.setUsername(dto.getUsername());
    korisnickiDetalji.setEmail(dto.getEmail());
    korisnickiDetalji.setLastname(dto.getLastName());
    korisnickiDetalji.setFirstname(dto.getFirstName());
    if (!dto.getPassword().equals(dto.getConfirmPassword())) {
      throw new InvalidConfirmPasswordException();
    }
    korisnickiDetalji.setPassword(passwordEncoder.encode(dto.getPassword()));
    entity.setKorisnickiDetalji(korisnickiDetalji);

    try {
      entity.setUloga(Uloga.fromValue(dto.getUloga()));
    } catch (IllegalArgumentException e) {
      throw new InvalidUlogaException(dto.getUloga());
    }

    return entity;
  }
}
