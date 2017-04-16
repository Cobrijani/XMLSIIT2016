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

  private final AmandmanProperties amandmanProperties = new AmandmanProperties();

  @Getter
  @Setter
  public static class Security {

    private String token = "X-Auth-Token";

    private String secret;

    private long tokenValidityInSeconds = 1800000;

    private long tokenValidityInSecondsForRememberMe = 259200000;
  }

  @Getter
  @Setter
  public static class AktProperties {

    private String actUri = "/api/v1/aktovi/{id}.xml";

  }

  @Getter
  @Setter
  public static class AmandmanProperties {

    private String amandmanUri = "/api/v1/amandmani/{id}.xml";

  }
}
