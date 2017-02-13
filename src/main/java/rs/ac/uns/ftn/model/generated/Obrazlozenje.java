
package rs.ac.uns.ftn.model.generated;

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
 *         &lt;element name="razlog_podnosenja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="objasnjenje_resenja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cilj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="procena_uticaja" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "razlogPodnosenja",
    "objasnjenjeResenja",
    "cilj",
    "procenaUticaja"
})
@XmlRootElement(name = "obrazlozenje", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Obrazlozenje {

    @XmlElement(name = "razlog_podnosenja", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String razlogPodnosenja;
    @XmlElement(name = "objasnjenje_resenja", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String objasnjenjeResenja;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String cilj;
    @XmlElement(name = "procena_uticaja", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String procenaUticaja;

    /**
     * Gets the value of the razlogPodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getRazlogPodnosenja() {
        return razlogPodnosenja;
    }

    /**
     * Sets the value of the razlogPodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRazlogPodnosenja(String value) {
        this.razlogPodnosenja = value;
    }

    /**
     * Gets the value of the objasnjenjeResenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getObjasnjenjeResenja() {
        return objasnjenjeResenja;
    }

    /**
     * Sets the value of the objasnjenjeResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setObjasnjenjeResenja(String value) {
        this.objasnjenjeResenja = value;
    }

    /**
     * Gets the value of the cilj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCilj() {
        return cilj;
    }

    /**
     * Sets the value of the cilj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCilj(String value) {
        this.cilj = value;
    }

    /**
     * Gets the value of the procenaUticaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getProcenaUticaja() {
        return procenaUticaja;
    }

    /**
     * Sets the value of the procenaUticaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-12T07:34:00+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setProcenaUticaja(String value) {
        this.procenaUticaja = value;
    }

}
