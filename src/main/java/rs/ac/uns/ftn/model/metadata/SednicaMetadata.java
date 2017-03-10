package rs.ac.uns.ftn.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Micko on 10-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SednicaMetadata {

  private String id;

  private String name;

  private String datum;

  private String mesto;
}
