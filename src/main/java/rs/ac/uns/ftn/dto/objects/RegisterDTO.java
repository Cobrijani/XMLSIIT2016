package rs.ac.uns.ftn.dto.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link rs.ac.uns.ftn.model.User} registration
 * Created by Arsa on 15-Feb-17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

  @NotNull
  protected String username;
  @NotNull
  protected String password;
  @NotNull
  protected String confirmPassword;
  @NotNull
  @Email
  protected String email;
  @NotNull
  protected String uloga;

  protected String firstName;

  protected String lastName;

}
