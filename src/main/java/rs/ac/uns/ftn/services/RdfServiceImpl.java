package rs.ac.uns.ftn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.marker.TriplesWriteHandle;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.RDFMimeTypes;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.model.rdf.Triplets;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for Rdf Manipulation such as reading and writing and extracting data from document
 * Created by SBratic on 3/5/2017.
 */
@Slf4j
@Service
public class RdfServiceImpl implements RdfService {


  private final TransformerFactory transformerFactory;

  private final GraphManager graphManager;

  @Value("classpath:schemas/grddl.xsl")
  private Resource grddlXslt;

  public RdfServiceImpl(TransformerFactory transformerFactory, GraphManager graphManager) {
    this.transformerFactory = transformerFactory;
    this.graphManager = graphManager;
  }


  @Override
  public void extractMetadata(Source source, Result result) {
    // Create transformation source
    StreamSource transformSource =
      Try.of(() ->
        new StreamSource(grddlXslt.getInputStream())
      )
        .getOrElseThrow(ex ->
          new InvalidServerConfigurationException()
        );


    // Initialize GRDDL transformer object
    Transformer grddlTransformer =
      Try.of(() -> transformerFactory.newTransformer(transformSource))
        .getOrElseThrow(ex -> new InvalidServerConfigurationException());

    // Set the indentation properties
    grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
    grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

    // Initialize transformation subject
    // Trigger the transformation

    try {
      grddlTransformer.transform(source, result);
    } catch (TransformerException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void extractMetadata(InputStream inputStream, OutputStream outputStream) {
    extractMetadata(new StreamSource(inputStream), new StreamResult(outputStream));
  }


  @Override
  public void extractAndWriteData(String data, String graphName) {
    final InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    extractAndWriteData(new StreamSource(in), graphName);

    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void extractAndWriteData(Document document, String graphName) {
    extractAndWriteData(new DOMSource(document), graphName);
  }

  @Override
  public void extractAndWriteData(Source source, String graphName) {
    graphManager.setDefaultMimetype(RDFMimeTypes.RDFXML);

    final ByteArrayOutputStream out = new ByteArrayOutputStream();

    extractMetadata(source, new StreamResult(out));

    TriplesWriteHandle handle = new InputStreamHandle(new ByteArrayInputStream(out.toByteArray())).
      withMimetype(RDFMimeTypes.RDFXML);

    graphManager.merge(graphName, handle);

    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void extractAndWriteData(InputStream stream, String graphName) {
    extractAndWriteData(new StreamSource(stream), graphName);
  }

  @Override
  public void writeData(TriplesWriteHandle triplesWriteHandle, String graphName) {
    graphManager.merge(graphName, triplesWriteHandle);
  }

  @Override
  public void transform(Source source, Result result) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();

      // Creating instance for serialization DOM model
      Transformer transformer = transformerFactory.newTransformer();

      // Indentation configuration
      transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      transformer.transform(source, result);

    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String readMetadata(String graphName) {
    DOMHandle domHandle = new DOMHandle();
    graphManager.read(graphName, domHandle).withMimetype(RDFMimeTypes.RDFXML);
    OutputStream out = new ByteArrayOutputStream();
    transform(new DOMSource(domHandle.get()), new StreamResult(out));
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return out.toString();
  }

  @Override
  public List<Triplets> handleResults(JacksonHandle jacksonHandle) {
    JsonNode tuples = jacksonHandle.get().path("results").path("bindings");

    List<Triplets> result = new ArrayList<>();
    tuples.forEach(row -> {
      String subject = row.path("s").path("value").asText();
      String predicate = row.path("p").path("value").asText();
      String object = row.path("o").path("value").asText();

      if (!"".equals(subject)) {
        result.add(new Triplets(subject, predicate, object));
      }

    });

    return result;

  }


}
