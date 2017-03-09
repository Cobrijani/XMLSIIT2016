
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;element name="izmene">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded">
 *                     &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}izmena"/>
 *                     &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}mala_izmena"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}obrazlozenje"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}uobicajeniAtributi"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateCreated"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}dateModified"/>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}createdBy"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "izmene",
    "obrazlozenje"
})
@XmlRootElement(name = "amandman", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Amandman {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Amandman.Izmene izmene;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Obrazlozenje obrazlozenje;
    @XmlAttribute(name = "dateCreated", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateCreated;
    @XmlAttribute(name = "dateModified", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateModified;
    @XmlAttribute(name = "createdBy", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String createdBy;
    @XmlAttribute(name = "naziv", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String naziv;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

    /**
     * Gets the value of the izmene property.
     * 
     * @return
     *     possible object is
     *     {@link Amandman.Izmene }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Amandman.Izmene getIzmene() {
        return izmene;
    }

    /**
     * Sets the value of the izmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amandman.Izmene }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIzmene(Amandman.Izmene value) {
        this.izmene = value;
    }

    /**
     * Gets the value of the obrazlozenje property.
     * 
     * @return
     *     possible object is
     *     {@link Obrazlozenje }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Obrazlozenje getObrazlozenje() {
        return obrazlozenje;
    }

    /**
     * Sets the value of the obrazlozenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obrazlozenje }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setObrazlozenje(Obrazlozenje value) {
        this.obrazlozenje = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateModified(XMLGregorianCalendar value) {
        this.dateModified = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
     *         &lt;choice maxOccurs="unbounded">
     *           &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}izmena"/>
     *           &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}mala_izmena"/>
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
        "izmenaOrMalaIzmena"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Izmene {

        @XmlElements({
            @XmlElement(name = "izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = Izmena.class),
            @XmlElement(name = "mala_izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = MalaIzmena.class)
        })
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<Object> izmenaOrMalaIzmena;

        /**
         * Gets the value of the izmenaOrMalaIzmena property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the izmenaOrMalaIzmena property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIzmenaOrMalaIzmena().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Izmena }
         * {@link MalaIzmena }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-09T01:54:40+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<Object> getIzmenaOrMalaIzmena() {
            if (izmenaOrMalaIzmena == null) {
                izmenaOrMalaIzmena = new ArrayList<Object>();
            }
            return this.izmenaOrMalaIzmena;
        }

    }

}
