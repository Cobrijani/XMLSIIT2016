package rs.ac.uns.ftn.security.jwt;

import org.springframework.security.core.Authentication;

/**
 * Created by SBratic on 10/31/2016.
 */
public interface TokenProvider {

  String createToken(Authentication authentication, Boolean rememberMe);

  boolean validateToken(String token);

  Authentication getAuthentication(String token);
}
