package rs.ac.uns.ftn.security;

/**
 * Created by SBratic on 10/31/2016.
 */
public class AuthoritiesConstants {

  public static final String USER = "ROLE_USER";
  public static final String VERIFIER = "ROLE_VERIFIER";
  public static final String ANONYMOUS = "ROLE_ANONYMOUS";
  public static String ADMIN = "ROLE_ADMIN";

  private AuthoritiesConstants() {
    throw new UnsupportedOperationException();
  }
}
