
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}naziv"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateCreated" minOccurs="0"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateModified" minOccurs="0"/>
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
    "naziv",
    "dateCreated",
    "dateModified"
})
@XmlRootElement(name = "zaglavlje")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Zaglavlje {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Naziv naziv;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DateCreated dateCreated;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DateModified dateModified;

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link Naziv }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Naziv getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Naziv }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNaziv(Naziv value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link DateCreated }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DateCreated getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateCreated }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateCreated(DateCreated value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateModified property.
     * 
     * @return
     *     possible object is
     *     {@link DateModified }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DateModified getDateModified() {
        return dateModified;
    }

    /**
     * Sets the value of the dateModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateModified }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateModified(DateModified value) {
        this.dateModified = value;
    }

}
