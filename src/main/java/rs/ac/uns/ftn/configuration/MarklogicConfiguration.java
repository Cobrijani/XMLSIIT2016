package rs.ac.uns.ftn.configuration;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.SPARQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import rs.ac.uns.ftn.properties.MarkLogicProperties;

import javax.xml.transform.TransformerFactory;

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
    return DatabaseClientFactory
      .newClient(markLogicProperties.getConnectivity().getHost(),
        markLogicProperties.getConnectivity().getPort(),
        markLogicProperties.getConnectivity().getDatabaseName(),
        markLogicProperties.getConnectivity().getUser(),
        markLogicProperties.getConnectivity().getPassword(),
        markLogicProperties.getConnectivity().getAuthenticationType());
  }

  @Bean
  @Scope("prototype")
  public XMLDocumentManager xmlDocumentManager() {
    return databaseClient().newXMLDocumentManager();
  }


  @Bean
  @Scope("prototype")
  public QueryManager queryManager() {
    return databaseClient().newQueryManager();
  }

  @Bean
  @Scope("prototype")
  public GraphManager graphManager() {
    return databaseClient().newGraphManager();
  }

  @Bean
  public TransformerFactory transformerFactory() {
    return TransformerFactory.newInstance();
  }

  @Bean
  @Scope("prototype")
  public SPARQLQueryManager sparqlQueryManager() {
    return databaseClient().newSPARQLQueryManager();
  }
}
