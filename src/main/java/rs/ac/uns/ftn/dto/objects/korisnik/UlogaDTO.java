package rs.ac.uns.ftn.dto.objects.korisnik;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by Arsa on 15-Feb-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UlogaDTO {

  @NotNull
  private String value;
}
