package rs.ac.uns.ftn.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration associated with {@link ModelMapper}
 * Created by Arsa on 16-Feb-17.
 */
@Configuration
public class ModelMapperConfiguration {

  /**
   * Returns configured instance of {@link ModelMapper}
   *
   * @return instance of {@link ModelMapper}
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
