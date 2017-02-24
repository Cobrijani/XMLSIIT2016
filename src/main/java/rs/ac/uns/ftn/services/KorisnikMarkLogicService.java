package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.exceptions.KorisnikAlreadyExistsException;
import rs.ac.uns.ftn.exceptions.KorisnikNotFoundException;
import rs.ac.uns.ftn.model.korisnici.Korisnik;

import java.util.Arrays;
import java.util.List;

import static rs.ac.uns.ftn.util.XMLUtil.*;

/**
 * Created by SBratic on 1/18/2017.
 */
@Service
@Slf4j
public class KorisnikMarkLogicService implements KorisnikService {

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private static final String KORISNIK_REF = "/korisnik.xml";

  private static final String KORISNIK_FORMAT = "/korisnici/%s.xml";

  private final IdentifierGenerator identifierGenerator;

  @Autowired
  public KorisnikMarkLogicService(XMLDocumentManager documentManager,
                                  QueryManager queryManager,
                                  IdentifierGenerator identifierGenerator) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.identifierGenerator = identifierGenerator;
  }


  @Override
  public List<Korisnik> findAll() {
    return this.findAll(null);
  }

  @Override
  public List<Korisnik> findAll(Pageable pageable) {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(KORISNIK_REF);

    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    return convertSearchHandle(searchHandle, documentManager, Korisnik.class);
  }

  @Override
  public Korisnik findById(String id) {
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(KORISNIK_FORMAT);
    JAXBHandle<Korisnik> handle = getJaxbHandle(Korisnik.class);
    documentManager.read(getDocumentId(KORISNIK_FORMAT, id), documentMetadataHandle, handle);
    return handle.get();
  }

  @Override
  public Korisnik findByUsername(String username) {
    StructuredQueryBuilder queryBuilder = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition structuredQueryDefinition =
      queryBuilder.and(queryBuilder.term(username), queryBuilder.collection(KORISNIK_REF));

    SearchHandle searchHandle = new SearchHandle();

    queryManager.search(structuredQueryDefinition, searchHandle);

    return convertSearchHandle(searchHandle, documentManager, Korisnik.class).stream().findFirst().orElseThrow(KorisnikNotFoundException::new);

  }

  @Override
  public Korisnik saveKorisnik(Korisnik korisnik) {

    try {
      this.findByUsername(korisnik.getKorisnickiDetalji().getUsername());
      throw new KorisnikAlreadyExistsException("Korisnik with username: " + korisnik.getKorisnickiDetalji().getUsername() + " already exists!");
    } catch (KorisnikNotFoundException ignored) {
    }

    korisnik.setId(identifierGenerator.generateIdentity());
    DocumentMetadataHandle documentMetadataHandle = new DocumentMetadataHandle();
    documentMetadataHandle.getCollections().add(KORISNIK_REF);


    JAXBHandle<Korisnik> handle = getJaxbHandle(Korisnik.class);
    handle.set(korisnik);
    documentManager.write(getDocumentId(KORISNIK_FORMAT, korisnik.getId()), documentMetadataHandle, handle);
    return korisnik;

  }

  @Override
  public void deleteKorisnikById(String id) {
    documentManager.delete(getDocumentId(KORISNIK_REF, id));
  }

  @Override
  public void deleteAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(KORISNIK_REF);
    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    Arrays.stream(searchHandle.getMatchResults()).map(MatchDocumentSummary::getUri).forEach(documentManager::delete);
    log.info("All korisnik documents deleted");
  }
}
