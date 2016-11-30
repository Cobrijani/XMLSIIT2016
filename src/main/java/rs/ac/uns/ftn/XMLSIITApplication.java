package rs.ac.uns.ftn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.ftn.model.Authority;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.properties.MarkLogicProperties;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.repositories.AuthorityRepository;
import rs.ac.uns.ftn.repositories.UserRepository;
import rs.ac.uns.ftn.security.AuthoritiesConstants;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties({XMLSIITProperties.class, MarkLogicProperties.class})
public class XMLSIITApplication {

  private static final Logger log = LoggerFactory.getLogger(XMLSIITApplication.class);


  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(XMLSIITApplication.class);
    Environment env = app.run(args).getEnvironment();
    log.info("\n----------------------------------------------------------\n\t" +
        "Application '{}' is running! Access URLs:\n\t" +
        "Local: \t\thttp://127.0.0.1:{}\n\t" +
        "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty("spring.application.name"),
      env.getProperty("server.port"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"));

  }

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthorityRepository authorityRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Bean
  public CommandLineRunner seedCLR() {
    return args -> {
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

    };
  }
}
