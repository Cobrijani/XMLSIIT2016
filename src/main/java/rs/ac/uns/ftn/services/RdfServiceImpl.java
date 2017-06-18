package rs.ac.uns.ftn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.Transaction;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.marker.TriplesWriteHandle;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.RDFMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
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

import static rs.ac.uns.ftn.constants.XmlNamespaces.*;

/**
 * Service for Rdf Manipulation such as reading and writing and extracting data from document
 * Created by SBratic on 3/5/2017.
 */
@Slf4j
@Service
public class RdfServiceImpl implements RdfService {


  private final TransformerFactory transformerFactory;

  private final GraphManager graphManager;

  private final SPARQLQueryManager sparqlQueryManager;

  @Value("classpath:schemas/grddl.xsl")
  private Resource grddlXslt;

  public RdfServiceImpl(TransformerFactory transformerFactory, GraphManager graphManager, SPARQLQueryManager sparqlQueryManager) {
    this.transformerFactory = transformerFactory;
    this.graphManager = graphManager;
    this.sparqlQueryManager = sparqlQueryManager;
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

  @Override
  public void updateTripleAkt(String id, String newValue, String predicate, String graphName) {

    String resource = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt/";
    predicate = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/" + predicate;

    String queryDefinition =
      "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> \n" +
        " WITH <" + graphName + ">" +
        " DELETE { <" + resource + id + "> <" + predicate + ">  ?o} " +
        " INSERT { <" + resource + id + "> <" + predicate + "> '" + newValue + "'^^<string> }" +
        " WHERE  { <" + resource + id + "> <" + predicate + ">  ?o}";
    SPARQLQueryDefinition query = sparqlQueryManager
      .newQueryDefinition(queryDefinition);

    sparqlQueryManager.executeUpdate(query);
  }

  private SPARQLQueryDefinition createDeleteQueryDefinition(String id, String predicate, String graphName) {
    final StringBuilder sparqlStringBuilder = new StringBuilder();

    final String aktId = "<" + AKT + "/" + id + ">";
    final String aktPred = "<" + PRED + predicate + ">";

    sparqlStringBuilder.append("PREFIX xs: <").append(XS).append("> \n")
      .append("WITH <").append(graphName).append(">")
      .append("DELETE { ").append(aktId).append(" ").append(aktPred).append(" ?o}")
      .append("WHERE {").append(aktId).append(" ").append(aktPred).append(" ?o}");

    return sparqlQueryManager.newQueryDefinition(sparqlStringBuilder.toString());
  }

  @Override
  public void deleteTripleAkt(String id, String predicate, String graphName) {
    sparqlQueryManager.executeUpdate(createDeleteQueryDefinition(id, predicate, graphName));
  }

  @Override
  public void deleteTripleAkt(String id, String predicate, String graphName, Transaction transaction) {
    sparqlQueryManager.executeUpdate(createDeleteQueryDefinition(id, predicate, graphName), transaction);
  }


}
