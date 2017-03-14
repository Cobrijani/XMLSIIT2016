
package rs.ac.uns.ftn.model.generated.sednica;

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
@XmlRootElement(name = "zaglavlje", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
public class Zaglavlje {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata", required = true)
    protected Naziv naziv;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected DateCreated dateCreated;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected DateModified dateModified;

    /**
     * Gets the value of the naziv property.
     *
     * @return
     *     possible object is
     *     {@link Naziv }
     *
     */
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
    public void setDateModified(DateModified value) {
        this.dateModified = value;
    }

}
