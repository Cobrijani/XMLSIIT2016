package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.dto.amandman.AmandmanForSednicaDTO;
import rs.ac.uns.ftn.model.AmandmanMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;

import java.util.List;

/**
 * Created by Arsa on 07-Mar-17.
 */
public interface AmandmanService {
  Amandman findById(String id);

  <T> T findById(String id, Class<T> readAs);

  void removeById(String id);

  List<Amandman> findAll(Pageable pageable);

  List<Amandman> findAllContaining(Pageable pageable, String term);

  void add(Amandman amandman);

  void deleteAmandmanById(String id);

  void deleteAll();

  List<AmandmanMetadata> getMetadata(Pageable pageable);

  List<AmandmanMetadata> getMetadata(Pageable pageable, AmandmanMetadataPredicate amandmanMetadataPredicate);

  Page<AmandmanMetadata> getMetadataPage(Pageable pageable, AmandmanMetadataPredicate amandmanMetadataPredicate);

  AmandmanForSednicaDTO putAmandman(String id, AmandmanForSednicaDTO amDTO);
}
