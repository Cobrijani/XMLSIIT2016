package rs.ac.uns.ftn.properties;

/**
 * Created by SBratic on 10/27/2016.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "xmlsiit-app", ignoreUnknownFields = false)
public class XMLSIITProperties {

  private final Security security = new Security();

  public Security getSecurity() {
    return security;
  }

  public static class Security {

    private String token = "X-Auth-Token";

    private String secret;

    private long tokenValidityInSeconds = 1800;

    private long tokenValidityInSecondsForRememberMe = 2592000;

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public String getSecret() {
      return secret;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }

    public long getTokenValidityInSeconds() {
      return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
      this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public long getTokenValidityInSecondsForRememberMe() {
      return tokenValidityInSecondsForRememberMe;
    }

    public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
      this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
    }
  }
}
