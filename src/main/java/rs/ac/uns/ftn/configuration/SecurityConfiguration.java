package rs.ac.uns.ftn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.security.Http401UnauthorizedEntryPoint;
import rs.ac.uns.ftn.security.csrf.CSRFConfigurer;
import rs.ac.uns.ftn.security.jwt.JWTConfigurer;
import rs.ac.uns.ftn.security.jwt.TokenProvider;

/**
 * Configuration for Spring Security
 * Created by SBratic on 10/27/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


  private final TokenProvider tokenProvider;

  private final Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;

  private final XMLSIITProperties XMLSIITProperties;

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsService userDetailsService;

  @Autowired
  public SecurityConfiguration(
    TokenProvider tokenProvider,
    Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint,
    XMLSIITProperties XMLSIITProperties,
    PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    this.tokenProvider = tokenProvider;
    this.http401UnauthorizedEntryPoint = http401UnauthorizedEntryPoint;
    this.XMLSIITProperties = XMLSIITProperties;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {

    web
      .debug(true)
      .ignoring().antMatchers("/styles/**");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {


    http
      .exceptionHandling()
      .authenticationEntryPoint(http401UnauthorizedEntryPoint)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .apply(jwtConfigurer())
      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/api/v1/authenticate").permitAll()
      .anyRequest().permitAll();


  }

  private JWTConfigurer jwtConfigurer() {
    return new JWTConfigurer(tokenProvider, XMLSIITProperties);
  }
}
