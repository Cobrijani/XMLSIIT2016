package rs.ac.uns.ftn.security.csrf;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by SBratic on 11/3/2016.
 */
public class CsrfFilter extends OncePerRequestFilter {

  public static final String CSRF_HEADER_NAME = "XSRF-TOKEN";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final Optional<CsrfToken> token = Optional.ofNullable((CsrfToken) request.getAttribute(CsrfToken.class.getName()));
    final Optional<Cookie> cookie = Optional.ofNullable(WebUtils.getCookie(request, CSRF_HEADER_NAME));



  }
}
