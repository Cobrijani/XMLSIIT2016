package rs.ac.uns.ftn;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.semantics.GraphManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.ftn.model.korisnici.KorisnickiDetalji;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.model.korisnici.Uloga;
import rs.ac.uns.ftn.properties.MarkLogicProperties;
import rs.ac.uns.ftn.properties.XMLSIITProperties;
import rs.ac.uns.ftn.services.*;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties({XMLSIITProperties.class, MarkLogicProperties.class})
public class XMLSIITApplication {

  private static final Logger log = LoggerFactory.getLogger(XMLSIITApplication.class);

  private final DatabaseClient databaseClient;

  public XMLSIITApplication(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

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

  @PreDestroy
  public void release() {
    databaseClient.release();
  }


  @Bean
  public CommandLineRunner recreateData(GraphManager graphManager, KorisnikService korisnikService, AktService aktService, SednicaService sednicaService, AmandmanService amandmanService, IdentifierGenerator identifierGenerator, PasswordEncoder passwordEncoder) {
    return (args -> {
      korisnikService.deleteAll();
      aktService.deleteAll();
      sednicaService.deleteAll();
      amandmanService.deleteAll();
      graphManager.deleteGraphs();

      final Korisnik korisnik = new Korisnik();
      korisnik.setId(identifierGenerator.generateIdentity());
      final KorisnickiDetalji korisnickiDetalji = new KorisnickiDetalji();
      korisnickiDetalji.setFirstname("gradjanin");
      korisnickiDetalji.setLastname("gradjanin");
      korisnickiDetalji.setPassword(passwordEncoder.encode("gradjanin"));
      korisnickiDetalji.setEmail("gradjanin@gmail.com");
      korisnickiDetalji.setUsername("gradjanin");
      korisnik.setKorisnickiDetalji(korisnickiDetalji);
      korisnik.setUloga(Uloga.GRADJANIN);
      korisnikService.saveKorisnik(korisnik);


      final Korisnik korisnik1 = new Korisnik();
      korisnik1.setId(identifierGenerator.generateIdentity());
      final KorisnickiDetalji korisnickiDetalji1 = new KorisnickiDetalji();
      korisnickiDetalji1.setFirstname("odbornik");
      korisnickiDetalji1.setLastname("odbornik");
      korisnickiDetalji1.setPassword(passwordEncoder.encode("odbornik"));
      korisnickiDetalji1.setEmail("odbornik@gmail.com");
      korisnickiDetalji1.setUsername("odbornik");
      korisnik1.setKorisnickiDetalji(korisnickiDetalji1);
      korisnik1.setUloga(Uloga.ODBORNIK);
      korisnikService.saveKorisnik(korisnik1);

      final Korisnik korisnik2 = new Korisnik();
      korisnik2.setId(identifierGenerator.generateIdentity());
      final KorisnickiDetalji korisnickiDetalji2 = new KorisnickiDetalji();
      korisnickiDetalji2.setFirstname("predsednik");
      korisnickiDetalji2.setLastname("predsednik");
      korisnickiDetalji2.setPassword(passwordEncoder.encode("predsednik"));
      korisnickiDetalji2.setEmail("predsednik@gmail.com");
      korisnickiDetalji2.setUsername("predsednik");
      korisnik2.setKorisnickiDetalji(korisnickiDetalji2);
      korisnik2.setUloga(Uloga.PREDSEDNIK);
      korisnikService.saveKorisnik(korisnik2);

    });
  }
}
