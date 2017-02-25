
package rs.ac.uns.ftn.model.generated;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}potacka"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}alineja"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}clan"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}stav"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}tacka"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "potacka",
    "alineja",
    "clan",
    "stav",
    "tacka"
})
@XmlRootElement(name = "resenje", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Resenje {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Potacka potacka;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Alineja alineja;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Clan clan;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Stav stav;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Tacka tacka;

    /**
     * Gets the value of the potacka property.
     * 
     * @return
     *     possible object is
     *     {@link Potacka }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Potacka getPotacka() {
        return potacka;
    }

    /**
     * Sets the value of the potacka property.
     * 
     * @param value
     *     allowed object is
     *     {@link Potacka }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPotacka(Potacka value) {
        this.potacka = value;
    }

    /**
     * Gets the value of the alineja property.
     * 
     * @return
     *     possible object is
     *     {@link Alineja }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Alineja getAlineja() {
        return alineja;
    }

    /**
     * Sets the value of the alineja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Alineja }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAlineja(Alineja value) {
        this.alineja = value;
    }

    /**
     * Gets the value of the clan property.
     * 
     * @return
     *     possible object is
     *     {@link Clan }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Clan getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Clan }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setClan(Clan value) {
        this.clan = value;
    }

    /**
     * Gets the value of the stav property.
     * 
     * @return
     *     possible object is
     *     {@link Stav }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Stav getStav() {
        return stav;
    }

    /**
     * Sets the value of the stav property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stav }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStav(Stav value) {
        this.stav = value;
    }

    /**
     * Gets the value of the tacka property.
     * 
     * @return
     *     possible object is
     *     {@link Tacka }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Tacka getTacka() {
        return tacka;
    }

    /**
     * Sets the value of the tacka property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tacka }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTacka(Tacka value) {
        this.tacka = value;
    }

}
