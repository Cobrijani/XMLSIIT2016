package rs.ac.uns.ftn.configuration;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.logging.Logger;

/**
 * Logging configuration
 * Created by SBratic on 11/30/2016.
 */
@Configuration
public class LoggingConfiguration {


  @Bean
  @Scope("prototype")
  public Logger logger(InjectionPoint point) {
    return Logger.getLogger(point.getMember().getDeclaringClass().getName());
  }
}
