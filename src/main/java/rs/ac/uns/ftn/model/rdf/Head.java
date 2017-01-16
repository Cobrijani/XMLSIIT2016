
package rs.ac.uns.ftn.model.rdf;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}variable" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}link" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "variable",
  "link"
})
@XmlRootElement(name = "head", namespace = "http://www.w3.org/2007/SPARQL/results#")
public class Head {

  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected List<Variable> variable;
  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected List<Link> link;

  /**
   * Gets the value of the variable property.
   * <p>
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the variable property.
   * <p>
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getVariable().add(newItem);
   * </pre>
   * <p>
   * <p>
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Variable }
   */
  public List<Variable> getVariable() {
    if (variable == null) {
      variable = new ArrayList<Variable>();
    }
    return this.variable;
  }

  /**
   * Gets the value of the link property.
   * <p>
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the link property.
   * <p>
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getLink().add(newItem);
   * </pre>
   * <p>
   * <p>
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Link }
   */
  public List<Link> getLink() {
    if (link == null) {
      link = new ArrayList<Link>();
    }
    return this.link;
  }

}
