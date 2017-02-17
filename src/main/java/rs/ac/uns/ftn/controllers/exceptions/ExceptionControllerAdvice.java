package rs.ac.uns.ftn.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rs.ac.uns.ftn.exceptions.InvalidConfirmPasswordException;
import rs.ac.uns.ftn.exceptions.InvalidUlogaException;
import rs.ac.uns.ftn.exceptions.Error;

/**
 * Created by Arsa on 15-Feb-17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(InvalidUlogaException.class)
  public ResponseEntity<Error> invalidUloga(InvalidUlogaException e) {
    String uloga = e.getUloga();
    Error error = new Error(400, "Uloga [" + uloga + "] nije definisana");
    return new ResponseEntity<>(error, 	HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidConfirmPasswordException.class)
  public ResponseEntity<Error> invalidConfirmPassword(InvalidConfirmPasswordException e) {
    Error error = new Error(400, "Sifra i ponovljena sifra se ne slazu");
    return new ResponseEntity<>(error, 	HttpStatus.BAD_REQUEST);
  }
}
