package rs.ac.uns.ftn.services;

import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.dto.akt.AktDTO;
import rs.ac.uns.ftn.dto.akt.PutAktDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanDTO;
import rs.ac.uns.ftn.dto.amandman.AmandmanForSednicaDTO;
import rs.ac.uns.ftn.dto.sednica.SednicaDTO;
import rs.ac.uns.ftn.model.generated.Sednica;

import java.util.List;

/**
 * Created by Micko on 03-Mar-17.
 */
public interface SednicaService {

    Sednica findById(String id);

    <T> T findById(String id, Class<T> readAs);

    void removeById(String id);

    List<Sednica> findAll(Pageable pageable);

    void add(Sednica sednica, String[] akti, String[] amandmani);

    void deleteById(String id);

    void deleteAll();

    List<SednicaDTO> getMetadata(Pageable pageable);

    List<PutAktDTO> findSednicaAktsById(String id);

    List<AmandmanForSednicaDTO> findSednicaAmandmandsById(String id);

  void putSednica(String id, SednicaDTO sednicaDTO);
}
