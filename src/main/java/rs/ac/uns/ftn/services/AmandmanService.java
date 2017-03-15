package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.model.AktMetadata;
import rs.ac.uns.ftn.model.AmandmanMetadata;
import rs.ac.uns.ftn.model.generated.Amandman;

import java.util.List;

/**
 * Created by Arsa on 07-Mar-17.
 */
public interface AmandmanService {
  Amandman findById(String id);

  <T> T findById(String id, Class<T> readAs);

  void removeById(String id);

  List<Amandman> findAll(Pageable pageable);

  void add(Amandman amandman);

  void deleteAktById(String id);

  void deleteAll();

  List<AmandmanMetadata> getMetadata(Pageable pageable);
}
