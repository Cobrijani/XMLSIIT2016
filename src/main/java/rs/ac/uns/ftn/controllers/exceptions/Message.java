package rs.ac.uns.ftn.controllers.exceptions;

/**
 * Created by SBratic on 3/13/2017.
 */
public class Message {

  private String message;
  private SEVERITY severity;


  public enum SEVERITY {
    ERROR, SUCCESS, WARNING
  }


}
