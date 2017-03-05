package rs.ac.uns.ftn.services;

import com.marklogic.client.io.InputStreamHandle;
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

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;

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
    // Create transformation source
    StreamSource transformSource = new StreamSource(new File("src/main/resources/schemas/grddl.xsl"));

    // Initialize GRDDL transformer object
    Transformer grddlTransformer = null;
    try {
      grddlTransformer = transformerFactory.newTransformer(transformSource);
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }

    // Set the indentation properties
    if (grddlTransformer != null) {
      grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
    }
    if (grddlTransformer != null) {
      grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
    }

    // Initialize transformation subject
    StreamSource source = new StreamSource(inputStream);

    // Initialize result stream
    StreamResult result = new StreamResult(outputStream);

    // Trigger the transformation
    try {
      if (grddlTransformer != null) {
        grddlTransformer.transform(source, result);
      }
    } catch (TransformerException e) {
      e.printStackTrace();
    }
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


}
