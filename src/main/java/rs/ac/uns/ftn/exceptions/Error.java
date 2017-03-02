package rs.ac.uns.ftn.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Arsa on 15-Feb-17.
 */
@AllArgsConstructor
@Getter
public class Error {
  private int code;
  private String message;
}
