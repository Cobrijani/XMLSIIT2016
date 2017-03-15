package rs.ac.uns.ftn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SBratic on 3/13/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResult {

  private boolean isValid;

  private String message;

}
