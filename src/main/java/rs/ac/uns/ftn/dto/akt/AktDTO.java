package rs.ac.uns.ftn.dto.akt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SBratic on 2/25/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AktDTO {

  private String id;

  private String name;

  private String createdBy;

  private String dateCreated;

}
