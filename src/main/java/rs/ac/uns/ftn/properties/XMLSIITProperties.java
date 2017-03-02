package rs.ac.uns.ftn.properties;

/**
 * Application related properties
 * Created by SBratic on 10/27/2016.
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "xmlsiit-app", ignoreUnknownFields = false)
public class XMLSIITProperties {

  private final Security security = new Security();

  private final AktProperties aktProperties = new AktProperties();

  @Getter
  @Setter
  public static class Security {

    private String token = "X-Auth-Token";

    private String secret;

    private long tokenValidityInSeconds = 1800;

    private long tokenValidityInSecondsForRememberMe = 2592000;
  }

  @Getter
  @Setter
  public static class AktProperties {

    private String actUri = "/api/v1/aktovi/{id}.xml";

  }
}
