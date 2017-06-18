package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.akt.MergeAktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.model.AktMetadataPredicate;
import rs.ac.uns.ftn.model.generated.Akt;
import rs.ac.uns.ftn.model.generated.Amandman;
import rs.ac.uns.ftn.model.metadata.AktMetadata;
import rs.ac.uns.ftn.model.metadata.AmandmanMetadata;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBratic on 1/18/2017.
 */
public interface AktService {

  Akt findById(String id);

  <T> T findById(String id, Class<T> readAs);

  List<Akt> findAll(Pageable pageable);

  List<Akt> findAllContaining(Pageable pageable, String term);

  Akt add(Akt akt);

  void deleteAktById(String id);

  void deleteAll();

  List<AktMetadata> getMetadata(Pageable pageable);

  List<AktMetadata> getMetadata(Pageable pageable, AktMetadataPredicate aktMetadataPredicate);

  Page<AktMetadata> getMetadataPage(Pageable pageable);

  Page<AktMetadata> getMetadataPage(Pageable pageable, AktMetadataPredicate aktMetadataPredicate);

  List<AmandmanMetadata> findAktAmandmandsById(String id);

  PutAktDTO putAkt(String id, PutAktDTO aktDTO);

  AktDTO mergeAkt(Akt akt, ArrayList<Amandman> amandmans) throws JAXBException;

  String getSparqlResult(Pageable pageable, AktMetadataPredicate aktMetadataPredicate);

}
