package rs.ac.uns.ftn.model.rdf;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}binding" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="index" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "binding"
})
@XmlRootElement(name = "result", namespace = "http://www.w3.org/2007/SPARQL/results#")
public class Result {

  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected List<Binding> binding;
  @XmlAttribute(name = "index")
  @XmlSchemaType(name = "positiveInteger")
  protected BigInteger index;

  /**
   * Gets the value of the binding property.
   * <p>
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the binding property.
   * <p>
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getBinding().add(newItem);
   * </pre>
   * <p>
   * <p>
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Binding }
   */
  public List<Binding> getBinding() {
    if (binding == null) {
      binding = new ArrayList<Binding>();
    }
    return this.binding;
  }

  /**
   * Gets the value of the index property.
   *
   * @return possible object is
   * {@link BigInteger }
   */
  public BigInteger getIndex() {
    return index;
  }

  /**
   * Sets the value of the index property.
   *
   * @param value allowed object is
   *              {@link BigInteger }
   */
  public void setIndex(BigInteger value) {
    this.index = value;
  }

}
