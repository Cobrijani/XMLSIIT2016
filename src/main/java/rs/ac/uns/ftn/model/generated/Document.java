
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.document}state"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.document}result"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.document}results"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.document}graphState"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "state",
    "result",
    "results",
    "graphState"
})
public class Document {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true, defaultValue = "default")
    protected String state;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true, defaultValue = "default")
    protected String result;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true)
    protected Results results;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true)
    protected GraphState graphState;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link Results }
     *     
     */
    public Results getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link Results }
     *     
     */
    public void setResults(Results value) {
        this.results = value;
    }

    /**
     * Gets the value of the graphState property.
     * 
     * @return
     *     possible object is
     *     {@link GraphState }
     *     
     */
    public GraphState getGraphState() {
        return graphState;
    }

    /**
     * Sets the value of the graphState property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphState }
     *     
     */
    public void setGraphState(GraphState value) {
        this.graphState = value;
    }

}
