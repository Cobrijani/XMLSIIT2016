package rs.ac.uns.ftn.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SBratic on 11/3/2016.
 */
public class JWTToken {

  private String idToken;

  public JWTToken() {
  }

  public JWTToken(String idToken) {
    this.idToken = idToken;
  }

  @JsonProperty("id_token")
  public String getIdToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }


}
