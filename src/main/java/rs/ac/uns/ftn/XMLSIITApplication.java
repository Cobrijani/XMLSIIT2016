package rs.ac.uns.ftn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import rs.ac.uns.ftn.properties.MarkLogicProperties;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.KorisnikService;

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
        "Local: \t\thttp://localhost:{}\n\t" +
        "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty("spring.application.name"),
      env.getProperty("server.port"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"));

  }

  @Bean
  public CommandLineRunner flushData(KorisnikService korisnikService) {
    return (args -> {
      korisnikService.deleteAll();
    });
  }
}
