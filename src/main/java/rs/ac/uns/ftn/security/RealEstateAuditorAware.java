package rs.ac.uns.ftn.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.security.model.RealEstateUserDetails;

import java.util.Optional;

/**
 * Created by SBratic on 10/28/2016.
 */
@Component
public class RealEstateAuditorAware implements AuditorAware<User> {


  @Override
  public User getCurrentAuditor() {
    Optional<Authentication> authenticationOptional =
      Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());

    return authenticationOptional
      .filter(Authentication::isAuthenticated)
      .map(x -> ((RealEstateUserDetails) x.getPrincipal()).getUser())
      .orElse(null);
  }
}
