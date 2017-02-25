
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sadrzaj">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}stav" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}prosireniUobicajeniAtributi"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "opis",
    "sadrzaj"
})
@XmlRootElement(name = "clan")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Clan {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String opis;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Clan.Sadrzaj sadrzaj;
    @XmlAttribute(name = "redniBroj")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String redniBroj;
    @XmlAttribute(name = "naziv", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String naziv;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link Clan.Sadrzaj }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Clan.Sadrzaj getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Clan.Sadrzaj }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSadrzaj(Clan.Sadrzaj value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRedniBroj(String value) {
        this.redniBroj = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }


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
     *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}stav" maxOccurs="unbounded"/>
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
        "stav"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Sadrzaj {

        @XmlElement(required = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<Stav> stav;

        /**
         * Gets the value of the stav property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stav property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStav().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Stav }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-25T09:02:25+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<Stav> getStav() {
            if (stav == null) {
                stav = new ArrayList<Stav>();
            }
            return this.stav;
        }

    }

}
