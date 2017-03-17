package rs.ac.uns.ftn.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SBratic on 3/7/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AktMetadata {

  private String id;

  private String name;

  private String dateCreated;

  private String dateModified;
}
