package rs.ac.uns.ftn.model.rdf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SBratic on 3/6/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Triplets {

  private String sub;

  private String pred;

  private String obj;
}
