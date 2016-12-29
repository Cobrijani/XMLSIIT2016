
package rs.ac.uns.ftn.model.akt;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute ref="{http://parlament.gov.rs/akt}idRef"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "value"
})
@XmlRootElement(name = "referenca")
public class Referenca {

  @XmlValue
  protected String value;
  @XmlAttribute(name = "idRef", namespace = "http://parlament.gov.rs/akt")
  protected String idRef;

  /**
   * Gets the value of the value property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the idRef property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getIdRef() {
    return idRef;
  }

  /**
   * Sets the value of the idRef property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setIdRef(String value) {
    this.idRef = value;
  }

}
