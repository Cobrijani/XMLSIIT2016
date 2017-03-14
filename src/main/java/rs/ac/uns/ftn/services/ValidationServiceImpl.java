package rs.ac.uns.ftn.services;

import javaslang.control.Try;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import rs.ac.uns.ftn.exceptions.InvalidServerConfigurationException;
import rs.ac.uns.ftn.exceptions.ValidationException;
import rs.ac.uns.ftn.model.ValidationResult;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by SBratic on 3/13/2017.
 */
@Service
public class ValidationServiceImpl implements ValidationService {


  @Override
  public ValidationResult validate(Source xmlFile, Source[] xsdFile) throws ValidationException {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      Schema schema = factory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(xmlFile);
    } catch (SAXException | IOException e) {
      throw new ValidationException(e.getMessage());
    }

    return new ValidationResult(true, "Xml file is valid");
  }

  @Override
  public ValidationResult validate(File xmlFile, File[] xsdFile) throws ValidationException {
    return validate(new StreamSource(xmlFile),
      Arrays.stream(xsdFile).map(StreamSource::new).toArray(StreamSource[]::new));
  }

  @Override
  public ValidationResult validate(Document xmlFile, File[] xsdFile) throws ValidationException {
    return validate(new DOMSource(xmlFile),
      Arrays.stream(xsdFile).map(StreamSource::new).toArray(StreamSource[]::new));
  }

  @Override
  public ValidationResult validate(String xmlFile, File[] xsdFile) throws ValidationException {
    ByteArrayInputStream stream = new ByteArrayInputStream(xmlFile.getBytes(StandardCharsets.UTF_8));
    return validate(new StreamSource(stream),
      Arrays.stream(xsdFile).map(StreamSource::new).toArray(StreamSource[]::new));
  }

  @Override
  public ValidationResult validate(String xmlFile, Resource[] xsdFile) throws ValidationException {
    File[] files = Arrays.stream(xsdFile)
      .map(x -> Try.of(x::getFile).getOrElseThrow(ex -> new InvalidServerConfigurationException()))
      .toArray(File[]::new);
    return validate(xmlFile, files);
  }
}
