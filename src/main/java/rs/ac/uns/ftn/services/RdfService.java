package rs.ac.uns.ftn.services;

import com.marklogic.client.io.marker.TriplesWriteHandle;
import org.w3c.dom.Document;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.io.OutputStream;

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
}
