package rs.ac.uns.ftn.dto.objects.korisnik;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Arsa on 15-Feb-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikDTO {

  private String id;

  private KorisnickiDetaljiDTO korisnickiDetalji;

  private UlogaDTO uloga;

}
