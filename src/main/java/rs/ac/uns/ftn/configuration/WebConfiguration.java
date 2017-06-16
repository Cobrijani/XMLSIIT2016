package rs.ac.uns.ftn.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import rs.ac.uns.ftn.configuration.argumentresolvers.AktMetadataHandlerMethodArgumentResolver;
import rs.ac.uns.ftn.configuration.argumentresolvers.AmandmanMetadataHandlerMethodArgumentResolver;

import java.util.List;

/**
 * Created by SBratic on 3/19/2017.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class WebConfiguration extends WebMvcConfigurerAdapter {


  /**
   * {@inheritDoc}
   * <p>This implementation is empty.
   *
   * @param argumentResolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new AktMetadataHandlerMethodArgumentResolver());
    argumentResolvers.add(new AmandmanMetadataHandlerMethodArgumentResolver());
  }


}
