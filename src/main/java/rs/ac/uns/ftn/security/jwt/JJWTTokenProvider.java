package rs.ac.uns.ftn.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.properties.XMLSIITProperties;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by SBratic on 10/29/2016.
 */
@Component
public class JJWTTokenProvider implements TokenProvider {

  private final Logger log = LoggerFactory.getLogger(JJWTTokenProvider.class);

  private String secretKey;

  private static final String AUTHORITIES_KEY = "auth";

  private final XMLSIITProperties XMLSIITProperties;

  private long tokenValidityInSecondsForRememberMe;

  private long tokenValidityInSeconds;

  @Autowired
  public JJWTTokenProvider(XMLSIITProperties XMLSIITProperties) {
    this.XMLSIITProperties = XMLSIITProperties;
  }

  @PostConstruct
  public void init() {

    this.secretKey =
      XMLSIITProperties.getSecurity().getSecret();
    this.tokenValidityInSecondsForRememberMe =
      1000 * XMLSIITProperties.getSecurity().getTokenValidityInSecondsForRememberMe();
    this.tokenValidityInSeconds =
      1000 * XMLSIITProperties.getSecurity().getTokenValidityInSeconds();

  }


  @Override
  public String createToken(Authentication authentication, Boolean rememberMe) {
    String authorities =
      authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    long now = (new Date()).getTime();

    Date validity;

    if (rememberMe) {
      validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
    } else {
      validity = new Date(now + this.tokenValidityInSeconds);
    }

    return Jwts.builder()
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .setExpiration(validity)
      .compact();
  }


  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.info(String.format("Invalid JWT signature : %s", ex.getMessage()));
      return false;
    }
  }

  @Override
  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .getBody();


    Collection<? extends GrantedAuthority> authorities =
      Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());


    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }
}
