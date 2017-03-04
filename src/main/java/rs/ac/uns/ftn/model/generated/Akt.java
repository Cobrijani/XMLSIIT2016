
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}preambula" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}deo" maxOccurs="unbounded"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}clan" maxOccurs="20" minOccurs="2"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}glava" maxOccurs="unbounded"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}uobicajeniAtributi"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}createdBy"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateCreated"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateModified"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "preambula",
    "deo",
    "clan",
    "glava"
})
@XmlRootElement(name = "akt", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Akt {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String preambula;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Deo> deo;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Clan> clan;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Glava> glava;
    @XmlAttribute(name = "createdBy", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String createdBy;
    @XmlAttribute(name = "dateCreated", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateCreated;
    @XmlAttribute(name = "dateModified", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateModified;
    @XmlAttribute(name = "naziv", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String naziv;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

    /**
     * Gets the value of the preambula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPreambula() {
        return preambula;
    }

    /**
     * Sets the value of the preambula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPreambula(String value) {
        this.preambula = value;
    }

    /**
     * Gets the value of the deo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Deo }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Deo> getDeo() {
        if (deo == null) {
            deo = new ArrayList<Deo>();
        }
        return this.deo;
    }

    /**
     * Gets the value of the clan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Clan }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Clan> getClan() {
        if (clan == null) {
            clan = new ArrayList<Clan>();
        }
        return this.clan;
    }

    /**
     * Gets the value of the glava property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the glava property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlava().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Glava }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Glava> getGlava() {
        if (glava == null) {
            glava = new ArrayList<Glava>();
        }
        return this.glava;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getDateModified() {
        return dateModified;
    }

    /**
     * Sets the value of the dateModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateModified(XMLGregorianCalendar value) {
        this.dateModified = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-04T05:03:22+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }

}
