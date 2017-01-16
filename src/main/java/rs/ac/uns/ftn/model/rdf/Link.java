
package rs.ac.uns.ftn.model.rdf;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.w3.org/2007/SPARQL/results#}hrefAttr"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "link", namespace = "http://www.w3.org/2007/SPARQL/results#")
public class Link {

  @XmlAttribute(name = "href", required = true)
  protected String href;

  /**
   * Gets the value of the href property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getHref() {
    return href;
  }

  /**
   * Sets the value of the href property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setHref(String value) {
    this.href = value;
  }

}
