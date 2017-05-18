
package rs.ac.uns.ftn.model.generated;

import javax.annotation.Generated;
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
    "results"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Document {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true, defaultValue = "default")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String state;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true, defaultValue = "default")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String result;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Results results;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setResults(Results value) {
        this.results = value;
    }

}
