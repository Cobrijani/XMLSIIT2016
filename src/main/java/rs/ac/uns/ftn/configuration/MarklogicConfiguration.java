package rs.ac.uns.ftn.configuration;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.properties.MarkLogicProperties;

/**
 * Configuration related to marklogic db
 * Created by SBratic on 11/30/2016.
 */
@Configuration
public class MarklogicConfiguration {

  @Autowired
  MarkLogicProperties markLogicProperties;

  @Bean
  public DatabaseClient databaseClient() {
    DatabaseClient dbClient =
      DatabaseClientFactory
        .newClient(markLogicProperties.getConnectivity().getHost(),
          markLogicProperties.getConnectivity().getPort(),
          markLogicProperties.getConnectivity().getDatabaseName(),
          markLogicProperties.getConnectivity().getUser(),
          markLogicProperties.getConnectivity().getPassword(),
          markLogicProperties.getConnectivity().getAuthenticationType());
    return dbClient;
  }

  @Bean
  public XMLDocumentManager xmlDocumentManager() {
    return databaseClient().newXMLDocumentManager();
  }


  @Bean
  public QueryManager queryManager() {
    return databaseClient().newQueryManager();
  }
}
