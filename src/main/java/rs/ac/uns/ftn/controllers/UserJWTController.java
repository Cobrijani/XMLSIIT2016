package rs.ac.uns.ftn.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.dto.objects.LoginDTO;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.security.jwt.JWTToken;
import rs.ac.uns.ftn.security.jwt.TokenProvider;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Class response for authenticating users
 * Created by SBratic on 11/3/2016.
 */
@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class UserJWTController {

  private final TokenProvider tokenProvider;

  private final AuthenticationManager authenticationManager;

  private final XMLSIITProperties XMLSIITProperties;


  @Autowired
  public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, XMLSIITProperties XMLSIITProperties, Logger logger) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
    this.XMLSIITProperties = XMLSIITProperties;
  }


  @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
      new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

    try {
      Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwtToken = tokenProvider.createToken(authentication, loginDTO.isRememberMe());

      response.addHeader(XMLSIITProperties.getSecurity().getToken(), "Bearer " + jwtToken);
      return ResponseEntity.ok(new JWTToken(jwtToken));

    } catch (AuthenticationException e) {
      log.error(e.toString());
      return new ResponseEntity<>(Collections.singletonMap("Authentication exception", e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
    }
  }
}
