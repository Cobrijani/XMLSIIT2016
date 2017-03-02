package rs.ac.uns.ftn.properties;

import com.marklogic.client.DatabaseClientFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties related to Marklogic database
 * Created by SBratic on 11/30/2016.
 */
@ConfigurationProperties(prefix = "marklogic", ignoreUnknownFields = false)
@Getter
@Setter
public class MarkLogicProperties {

  private Connectivity connectivity = new Connectivity();

  /**
   * Properties related to connectivity to Marklogic database
   */
  @Getter
  @Setter
  public static class Connectivity {

    private String user = "student12";
    private String password = "ftn";

    private String host = "147.91.177.194";
    private int port = 8000;
    private DatabaseClientFactory.Authentication authenticationType = DatabaseClientFactory.Authentication.DIGEST;


    private boolean useProxy = false;
    private String proxyHost;
    private String proxyPort;

    private String databaseName = "student12";

  }


}
