
package rs.ac.uns.ftn.model.generated;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


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
 *       &lt;anyAttribute/>
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
@XmlRootElement(name = "zaglavlje_sednica", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
public class ZaglavljeSednica {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata", required = true)
    protected Naziv naziv;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected DateCreated dateCreated;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected DateModified dateModified;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
