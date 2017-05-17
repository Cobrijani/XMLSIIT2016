package rs.ac.uns.ftn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SBratic on 3/15/2017.
 */

@Service(value = "XmlSchemaRegistry")
public class XmlSchemaRegistry implements Registry<String, Resource> {

  private final Map<String, Resource> schemaRegistry;

  @Value("classpath:schemas/akt.xsd")
  private Resource aktXsd;
  @Value("classpath:schemas/metadata.xsd")
  private Resource metaXsd;

  public XmlSchemaRegistry() {
    schemaRegistry = new HashMap<>();
  }


  @PostConstruct
  public void postConstruct() {
    schemaRegistry.put("akt", aktXsd);
    schemaRegistry.put("meta", metaXsd);
  }

  @PreDestroy
  public void preDestroy() {
    schemaRegistry.clear();
  }


  @Override
  public Resource getItemFromRegistry(String key) {
    return schemaRegistry.get(key);
  }
}
