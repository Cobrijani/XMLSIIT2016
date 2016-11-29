package rs.ac.uns.ftn.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import rs.ac.uns.ftn.properties.XMLSIITProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by SBratic on 10/30/2016.
 */
public class JWTFilter extends GenericFilterBean {

  private final TokenProvider tokenProvider;

  private final XMLSIITProperties XMLSIITProperties;


  public JWTFilter(TokenProvider tokenProvider, XMLSIITProperties XMLSIITProperties) {
    this.tokenProvider = tokenProvider;
    this.XMLSIITProperties = XMLSIITProperties;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    String token = resolveToken(request);

    if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
      Authentication authentication = this.tokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(XMLSIITProperties.getSecurity().getToken());
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
}
