package rs.ac.uns.ftn.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.objects.korisnik.KorisnikDTO;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.korisnici.Korisnik;
import rs.ac.uns.ftn.model.korisnici.Uloga;

/**
 * Configuration associated with {@link ModelMapper}
 * Created by Arsa on 16-Feb-17.
 */
@Configuration
public class ModelMapperConfiguration {

  /**
   * Returns configured instance of {@link ModelMapper}
   *
   * @return instance of {@link ModelMapper}
   */
  @Bean
  public ModelMapper modelMapper() {
    final ModelMapper modelMapper = new ModelMapper();
    modelMapper.addMappings(korisnikPropertyMap());
    modelMapper.addMappings(aktAktDTOPropertyMap());
    return modelMapper;
  }


  private PropertyMap<Korisnik, KorisnikDTO> korisnikPropertyMap() {
    return new PropertyMap<Korisnik, KorisnikDTO>() {
      @Override
      protected void configure() {
        map().setId(source.getId());
        map().getKorisnickiDetalji().setEmail(source.getKorisnickiDetalji().getEmail());
        map().getKorisnickiDetalji().setFirstname(source.getKorisnickiDetalji().getFirstname());
        map().getKorisnickiDetalji().setLastname(source.getKorisnickiDetalji().getLastname());
        map().getKorisnickiDetalji().setUsername(source.getKorisnickiDetalji().getUsername());
        map().setUloga(source.getUloga());
      }
    };
  }

  private Converter<String, Uloga> stringUlogaConverter() {
    return new AbstractConverter<String, Uloga>() {
      /**
       * Converts {@code source} to an instance of type {@code D}.
       *
       * @param source
       */
      @Override
      protected Uloga convert(String source) {
        return Uloga.valueOf(source);
      }
    };
  }

  private Converter<Uloga, String> ulogaStringConverter() {
    return new AbstractConverter<Uloga, String>() {
      @Override
      protected String convert(Uloga source) {
        return source.value();
      }
    };
  }

  private PropertyMap<Akt, AktDTO> aktAktDTOPropertyMap() {
    return new PropertyMap<Akt, AktDTO>() {
      @Override
      protected void configure() {
        map().setId(source.getId());
        map().setName(source.getNaziv());
        map().setDateCreated(source.getDateCreated().toString());
      }
    };
  }


}
