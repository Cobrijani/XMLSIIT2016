package rs.ac.uns.ftn.services;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.model.korisnici.Korisnik;

import java.util.List;

import static rs.ac.uns.ftn.util.XMLUtil.*;

/**
 * Created by SBratic on 1/18/2017.
 */
@Service
public class KorisnikMarkLogicService implements KorisnikService {

  private final XMLDocumentManager documentManager;

  private final QueryManager queryManager;

  private static final String KORISNIK_REF = "/korisnik.xml";

  private static final String KORISNIK_FORMAT = "/korisnici/%s.xml";

  private final PasswordEncoder passwordEncoder;

  private final IdentifierGenerator identifierGenerator;

  @Autowired
  public KorisnikMarkLogicService(XMLDocumentManager documentManager,
                                  QueryManager queryManager,
                                  PasswordEncoder passwordEncoder,
                                  IdentifierGenerator identifierGenerator) {
    this.documentManager = documentManager;
    this.queryManager = queryManager;
    this.passwordEncoder = passwordEncoder;
    this.identifierGenerator = identifierGenerator;
  }


  @Override
  public List<Korisnik> findAll() {
    StructuredQueryBuilder sb = queryManager.newStructuredQueryBuilder();
    StructuredQueryDefinition definition = sb.collection(KORISNIK_REF);

    SearchHandle searchHandle = new SearchHandle();
    queryManager.search(definition, searchHandle);

    return convertSearchHandle(searchHandle, documentManager, Korisnik.class);
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
    List<Korisnik> korisnici = findAll();//TODO ovo za sad u zivotu ne treba tako

    return korisnici
      .stream()
      .filter(x -> x.getKorisnickiDetalji().getUsername().equalsIgnoreCase(username))
      .findFirst().orElseThrow(NullPointerException::new);
  }

  @Override
  public Korisnik saveKorisnik(Korisnik korisnik) {
    korisnik.setId(identifierGenerator.generateIdentity());
    //korisnik.getKorisnickiDetalji().setPassword(passwordEncoder.encode(korisnik.getKorisnickiDetalji().getPassword()));
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
}
