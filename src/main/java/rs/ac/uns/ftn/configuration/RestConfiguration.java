package rs.ac.uns.ftn.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import static org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED;

/**
 * Created by SBratic on 11/3/2016.
 */
@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.setRepositoryDetectionStrategy(ANNOTATED);
    //only repositories annotated with @RepositoryRestSource to be exposed and created
  }
}
