package rs.ac.uns.ftn.dto.sednica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Micko on 04-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SednicaDTO {

  private String id;

  private String naziv;

  private String createdBy;

  private String dateCreated;

  private String datum;

  private String mesto;

}
