package rs.ac.uns.ftn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.model.Authority;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.repositories.AuthorityRepository;
import rs.ac.uns.ftn.repositories.UserRepository;
import rs.ac.uns.ftn.security.AuthoritiesConstants;

/**
 * Seed db with starting data
 * Created by SBratic on 12/29/2016.
 */
@Component
public class DbCommandLineRunner implements CommandLineRunner {

  private final UserRepository userRepository;

  private final AuthorityRepository authorityRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public DbCommandLineRunner(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
    this.passwordEncoder = passwordEncoder;
  }


  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    Authority roleAdmin = new Authority();
    Authority roleVerifier = new Authority();
    Authority roleUser = new Authority();

    roleAdmin.setName(AuthoritiesConstants.ADMIN);
    roleVerifier.setName(AuthoritiesConstants.VERIFIER);
    roleUser.setName(AuthoritiesConstants.USER);

    User admin = new User();
    User verifier = new User();
    User user = new User();

    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("admin"));
    admin.setEmail("adimin@gmail.com");
    admin.getAuthorities().add(roleAdmin);
    admin.setEnabled(true);

    verifier.setUsername("verifier");
    verifier.setPassword(passwordEncoder.encode("verifier"));
    verifier.setEmail("verifier@gmail.com");
    verifier.getAuthorities().add(roleVerifier);
    verifier.setEnabled(true);

    user.setUsername("user");
    user.setPassword(passwordEncoder.encode("user"));
    user.setEmail("user@gmail.com");
    user.getAuthorities().add(roleUser);
    user.setEnabled(true);
    userRepository.save(admin);
    userRepository.save(verifier);
    userRepository.save(user);

  }
}
