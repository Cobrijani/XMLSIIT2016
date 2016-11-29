package rs.ac.uns.ftn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.repositories.UserRepository;
import rs.ac.uns.ftn.security.model.RealEstateUserDetails;

import java.util.Optional;

/**
 * Created by SBratic on 10/27/2016.
 */
@Service
public class RealEstateUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public RealEstateUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    Optional<User> user = userRepository.findByUsername(s);

    return user
      .flatMap(x -> Optional.of(new RealEstateUserDetails(x)))
      .orElseThrow(() ->
        new UsernameNotFoundException(String.format("User with username %s was not found", s)));
  }
}
