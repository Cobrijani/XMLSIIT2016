
package rs.ac.uns.ftn.model.rdf;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "head",
    "results",
    "_boolean"
})
@XmlRootElement(name = "sparql")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Sparql {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Head head;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Results results;
    @XmlElement(name = "boolean")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean _boolean;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link Head }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Head getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link Head }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHead(Head value) {
        this.head = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link Results }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setResults(Results value) {
        this.results = value;
    }

    /**
     * Gets the value of the boolean property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isBoolean() {
        return _boolean;
    }

    /**
     * Sets the value of the boolean property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setBoolean(Boolean value) {
        this._boolean = value;
    }

}
