
package rs.ac.uns.ftn.model.generated.sednica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica}uobicajeniAtributi"/>
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="mesto" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "")
@XmlRootElement(name = "sednica", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
public class Sednica {

    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAttribute(name = "mesto")
    protected String mesto;
    @XmlAttribute(name = "createdBy", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected String createdBy;
    @XmlAttribute(name = "dateCreated", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateCreated;
    @XmlAttribute(name = "dateModified", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateModified;
    @XmlAttribute(name = "naziv", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected String naziv;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected String id;

    /**
     * Gets the value of the datum property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the mesto property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the createdBy property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
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
    public void setId(String value) {
        this.id = value;
    }

}
