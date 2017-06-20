package rs.ac.uns.ftn.services;

import com.marklogic.client.Transaction;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.marker.TriplesWriteHandle;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.model.rdf.Triplets;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by SBratic on 3/5/2017.
 */
public interface RdfService {

  void extractMetadata(Source source, Result result);

  void extractMetadata(InputStream inputStream, OutputStream outputStream);

  void extractAndWriteData(String data, String graphName);

  void extractAndWriteData(Document document, String graphName);

  void extractAndWriteData(Source source, String graphName);

  void extractAndWriteData(InputStream stream, String graphName);

  void writeData(TriplesWriteHandle triplesWriteHandle, String graphName);

  void transform(Source source, Result result);

  String readMetadata(String graphName);

  List<Triplets> handleResults(JacksonHandle jacksonHandle);

  void updateTripleAkt(String aktId, String newValue, String predicate, String graphName);

  void updateTripleAkt(String aktId, String newValue, String predicate, String graphName, Transaction transaction);

  void updateTripleAmandman(String aktId, String newValue, String predicate, String graphName, Transaction transaction);

  void deleteTripleAkt(String id, List<String> predicates, String graphName);

  void deleteTripleAkt(String id, List<String> predicates, String graphName, Transaction transaction);

  void deleteTripleAmandman(String id, List<String> predicates, String graphName, Transaction transaction);
}
