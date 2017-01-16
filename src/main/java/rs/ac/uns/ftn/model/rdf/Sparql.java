
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}head"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}results"/>
 *           &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}boolean"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "head",
  "results",
  "_boolean"
})
@XmlRootElement(name = "sparql", namespace = "http://www.w3.org/2007/SPARQL/results#")
public class Sparql {

  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#", required = true)
  protected Head head;
  @XmlElement(namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected Results results;
  @XmlElement(name = "boolean", namespace = "http://www.w3.org/2007/SPARQL/results#")
  protected Boolean _boolean;

  /**
   * Gets the value of the head property.
   *
   * @return possible object is
   * {@link Head }
   */
  public Head getHead() {
    return head;
  }

  /**
   * Sets the value of the head property.
   *
   * @param value allowed object is
   *              {@link Head }
   */
  public void setHead(Head value) {
    this.head = value;
  }

  /**
   * Gets the value of the results property.
   *
   * @return possible object is
   * {@link Results }
   */
  public Results getResults() {
    return results;
  }

  /**
   * Sets the value of the results property.
   *
   * @param value allowed object is
   *              {@link Results }
   */
  public void setResults(Results value) {
    this.results = value;
  }

  /**
   * Gets the value of the boolean property.
   *
   * @return possible object is
   * {@link Boolean }
   */
  public Boolean isBoolean() {
    return _boolean;
  }

  /**
   * Sets the value of the boolean property.
   *
   * @param value allowed object is
   *              {@link Boolean }
   */
  public void setBoolean(Boolean value) {
    this._boolean = value;
  }

}
