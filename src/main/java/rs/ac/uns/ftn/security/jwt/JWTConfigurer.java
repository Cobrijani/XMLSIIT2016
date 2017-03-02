package rs.ac.uns.ftn.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.ac.uns.ftn.properties.XMLSIITProperties;

/**
 * Created by SBratic on 10/30/2016.
 */
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


  private final TokenProvider tokenProvider;

  private final XMLSIITProperties XMLSIITProperties;


  public JWTConfigurer(TokenProvider tokenProvider, XMLSIITProperties XMLSIITProperties) {
    this.tokenProvider = tokenProvider;
    this.XMLSIITProperties = XMLSIITProperties;
  }

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    JWTFilter jwtFilter = new JWTFilter(tokenProvider, XMLSIITProperties);
    builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
