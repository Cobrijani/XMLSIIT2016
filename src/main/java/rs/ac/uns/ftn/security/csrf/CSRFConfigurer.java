package rs.ac.uns.ftn.security.csrf;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * Created by SBratic on 10/31/2016.
 */
public class CSRFConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Override
  public void configure(HttpSecurity builder) throws Exception {


  }
}
