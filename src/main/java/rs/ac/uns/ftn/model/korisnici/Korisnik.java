
package rs.ac.uns.ftn.model.korisnici;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="uloga" type="{http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici}uloga"/>
 *         &lt;element name="korisnickiDetalji" type="{http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici}korisnickiDetalji"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uloga",
    "korisnickiDetalji"
})
@XmlRootElement(name = "korisnik")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Korisnik {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Uloga uloga;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected KorisnickiDetalji korisnickiDetalji;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

    /**
     * Gets the value of the uloga property.
     * 
     * @return
     *     possible object is
     *     {@link Uloga }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Uloga getUloga() {
        return uloga;
    }

    /**
     * Sets the value of the uloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uloga }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUloga(Uloga value) {
        this.uloga = value;
    }

    /**
     * Gets the value of the korisnickiDetalji property.
     * 
     * @return
     *     possible object is
     *     {@link KorisnickiDetalji }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public KorisnickiDetalji getKorisnickiDetalji() {
        return korisnickiDetalji;
    }

    /**
     * Sets the value of the korisnickiDetalji property.
     * 
     * @param value
     *     allowed object is
     *     {@link KorisnickiDetalji }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setKorisnickiDetalji(KorisnickiDetalji value) {
        this.korisnickiDetalji = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }

}
