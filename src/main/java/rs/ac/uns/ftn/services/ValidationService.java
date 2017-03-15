package rs.ac.uns.ftn.services;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.exceptions.ValidationException;
import rs.ac.uns.ftn.model.ValidationResult;

import javax.xml.transform.Source;
import java.io.File;

/**
 * Created by SBratic on 3/13/2017.
 */
public interface ValidationService {


  ValidationResult validate(Source xmlFile, Source[] xsdFile) throws ValidationException;

  ValidationResult validate(File xmlFile, File[] xsdFile) throws ValidationException;

  ValidationResult validate(Document xmlFile, File[] xsdFile) throws ValidationException;

  ValidationResult validate(String xmlFile, File[] xsdFile) throws ValidationException;

  ValidationResult validate(String xmlFile, Resource[] xsdFile) throws ValidationException;

  ValidationResult validate(Document xmlFile, Resource[] xsdFile) throws ValidationException;

}
