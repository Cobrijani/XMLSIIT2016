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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.security.Http401UnauthorizedEntryPoint;
import rs.ac.uns.ftn.security.RealEstateUserDetailsService;
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


  private final RealEstateUserDetailsService realEstateUserDetailsService;

  private final TokenProvider tokenProvider;

  private final Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;

  private final XMLSIITProperties XMLSIITProperties;

  @Autowired
  public SecurityConfiguration(RealEstateUserDetailsService realEstateUserDetailsService, TokenProvider tokenProvider, Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint, XMLSIITProperties XMLSIITProperties) {
    this.realEstateUserDetailsService = realEstateUserDetailsService;
    this.tokenProvider = tokenProvider;
    this.http401UnauthorizedEntryPoint = http401UnauthorizedEntryPoint;
    this.XMLSIITProperties = XMLSIITProperties;
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(realEstateUserDetailsService)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {

    web
      .debug(true)
      .ignoring().antMatchers("/styles/**");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
      exceptionHandling()
      .authenticationEntryPoint(http401UnauthorizedEntryPoint)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .apply(jwtConfigurer())
      .and()
      .apply(csrfConfigurer())
      .and()
      .csrf()
    .disable()
      /*.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      .and()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/api/authenticate").permitAll()
      .anyRequest().authenticated()*/;


  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private JWTConfigurer jwtConfigurer() {
    return new JWTConfigurer(tokenProvider, XMLSIITProperties);
  }

  private CSRFConfigurer csrfConfigurer() {
    return new CSRFConfigurer();
  }

  @Bean
  public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
    return new SecurityEvaluationContextExtension();
  }


}
