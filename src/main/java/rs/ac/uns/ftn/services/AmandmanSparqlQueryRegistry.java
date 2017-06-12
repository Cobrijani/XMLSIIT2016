package rs.ac.uns.ftn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

/**
 * Created by Arsa on 6/12/2017.
 */
@Service(value = "AmandmanSparqlQueryRegistry")
public class AmandmanSparqlQueryRegistry implements Registry<String, Resource> {

  @Value("classpath:sparql/amandman.rq")
  private Resource amandmanSparql;

  @Value("classpath:sparql/amandmanCount.rq")
  private Resource amandmanCount;

  private final HashMap<String, Resource> registry;

  public AmandmanSparqlQueryRegistry() {
    registry = new HashMap<>();
  }

  @PostConstruct
  public void init() {
    registry.put("amandman.rq", amandmanSparql);
    registry.put("amandmanCount.rq", amandmanCount);
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
