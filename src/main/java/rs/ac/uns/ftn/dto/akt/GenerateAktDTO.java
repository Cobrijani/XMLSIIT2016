package rs.ac.uns.ftn.dto.akt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Lenovo on 17-Jun-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateAktDTO {
  private ArrayList<MergeAktDTO> akts;
}
