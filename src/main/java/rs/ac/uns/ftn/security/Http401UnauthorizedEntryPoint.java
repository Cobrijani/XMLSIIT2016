package rs.ac.uns.ftn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SBratic on 10/31/2016.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    log.debug("Authentication entry point access denied");
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
  }
}
