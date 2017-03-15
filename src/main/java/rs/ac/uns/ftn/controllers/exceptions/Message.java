package rs.ac.uns.ftn.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SBratic on 3/13/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

  private String message;
  private SEVERITY severity;


  public enum SEVERITY {
    ERROR, SUCCESS, WARNING, FATAL, INFO, TRACE, DEBUG
  }


}
