package rs.ac.uns.ftn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SBratic on 11/3/2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

  private String username;

  private String password;

  private boolean isRememberMe;

}
