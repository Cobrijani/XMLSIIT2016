package rs.ac.uns.ftn.model.rdf;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}uri"/>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}bnode"/>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}literal"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.w3.org/2007/SPARQL/results#}nameAttr"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "uri",
  "bnode",
  "literal"
})
@XmlRootElement(name = "binding", namespace = "http://www.w3.org/2007/SPARQL/results#")
public class Binding {

  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected String uri;
  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected String bnode;
  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected Literal literal;
  @XmlAttribute(name = "name", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String name;

  /**
   * Gets the value of the uri property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getUri() {
    return uri;
  }

  /**
   * Sets the value of the uri property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setUri(String value) {
    this.uri = value;
  }

  /**
   * Gets the value of the bnode property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getBnode() {
    return bnode;
  }

  /**
   * Sets the value of the bnode property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setBnode(String value) {
    this.bnode = value;
  }

  /**
   * Gets the value of the literal property.
   *
   * @return possible object is
   * {@link Literal }
   */
  public Literal getLiteral() {
    return literal;
  }

  /**
   * Sets the value of the literal property.
   *
   * @param value allowed object is
   *              {@link Literal }
   */
  public void setLiteral(Literal value) {
    this.literal = value;
  }

  /**
   * Gets the value of the name property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }

}
