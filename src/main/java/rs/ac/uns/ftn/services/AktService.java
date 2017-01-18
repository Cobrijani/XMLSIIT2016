package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.model.akt.Akt;

import java.util.List;

/**
 * Created by SBratic on 1/18/2017.
 */
public interface AktService {
  Akt findById(String id);

  void removeById(String id);

  List<Akt> findAll(Pageable pageable);

  void add(Akt akt);
}
