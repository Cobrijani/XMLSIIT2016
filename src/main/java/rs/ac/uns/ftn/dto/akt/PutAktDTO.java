package rs.ac.uns.ftn.dto.akt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Micko on 25-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutAktDTO {

  private String id;

  private String name;

  private String dateCreated;

  private String state;

  private String result;

  private Integer forVote;

  private Integer against;

  private Integer notVote;

}
