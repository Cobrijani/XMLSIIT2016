package rs.ac.uns.ftn.dto.akt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Micko on 16-Jun-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MergeAktDTO {

  private String aktId;
  private ArrayList<String> amandmanIds;
}
