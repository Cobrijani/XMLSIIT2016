package rs.ac.uns.ftn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

/**
 * Created by SBratic on 3/18/2017.
 */
@Service(value = "AktSparqlQueryRegistry")
public class AktSparqlQueryRegistry implements Registry<String, Resource> {

  @Value("classpath:sparql/akt/akt.rq")
  private Resource aktSparql;

  @Value("classpath:sparql/akt/aktCount.rq")
  private Resource aktCount;

  @Value("classpath:sparql/akt/aktCanBeDeleted.rq")
  private Resource aktCanBeDeleted;

  private final HashMap<String, Resource> registry;

  public AktSparqlQueryRegistry() {
    registry = new HashMap<>();
  }

  @PostConstruct
  public void init() {
    registry.put("akt.rq", aktSparql);
    registry.put("aktCount.rq", aktCount);
    registry.put("aktCanBeDeleted.rq", aktCanBeDeleted);

  }

  @PreDestroy
  public void destroy() {
    registry.clear();
  }

  @Override
  public Resource getItemFromRegistry(String key) {
    return registry.get(key);
  }
}
