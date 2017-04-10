package rs.ac.uns.ftn.dto.amandman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Micko on 17-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmandmanForSednicaDTO {

  private String id;

  private String naziv;

  private String dateCreated;

  private String aktId;

  private String state;

  private String result;

  private int forVote;

  private int against;

  private int notVote;
}
