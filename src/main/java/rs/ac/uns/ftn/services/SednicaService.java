package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.model.generated.Sednica;
import rs.ac.uns.ftn.model.metadata.SednicaMetadata;

import java.util.List;

/**
 * Created by Micko on 03-Mar-17.
 */
public interface SednicaService {

    Sednica findById(String id);

    <T> T findById(String id, Class<T> readAs);

    void removeById(String id);

    List<Sednica> findAll(Pageable pageable);

    void add(Sednica sednica);

    void deleteById(String id);

    void deleteAll();

    List<SednicaMetadata> getMetadata(Pageable pageable);
  }
