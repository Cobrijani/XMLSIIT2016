package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.model.generated.Akt;

import java.util.List;

/**
 * Created by SBratic on 1/18/2017.
 */
public interface AktService {

  Akt findById(String id);

  <T> T findById(String id, Class<T> readAs);

  void removeById(String id);

  List<Akt> findAll(Pageable pageable);

  void add(Akt akt);

  void deleteAktById(String id);

  void deleteAll();
}
