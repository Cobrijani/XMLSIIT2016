package rs.ac.uns.ftn.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Arsa on 14-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmandmanMetadata {

  private String id;

  private String name;

  private String dateCreated;

  private String dateModified;
}
