
package rs.ac.uns.ftn.model.generated.sednica;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica}zaglavlje_sednica"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica}informacije"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}id"/>
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
    "zaglavljeSednica",
    "informacije"
})
@XmlRootElement(name = "sednica", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
public class Sednica {

    @XmlElement(name = "zaglavlje_sednica", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica", required = true)
    protected ZaglavljeSednica zaglavljeSednica;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica", required = true)
    protected Informacije informacije;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected String id;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the zaglavljeSednica property.
     *
     * @return
     *     possible object is
     *     {@link ZaglavljeSednica }
     *
     */
    public ZaglavljeSednica getZaglavljeSednica() {
        return zaglavljeSednica;
    }

    /**
     * Sets the value of the zaglavljeSednica property.
     *
     * @param value
     *     allowed object is
     *     {@link ZaglavljeSednica }
     *
     */
    public void setZaglavljeSednica(ZaglavljeSednica value) {
        this.zaglavljeSednica = value;
    }

    /**
     * Gets the value of the informacije property.
     *
     * @return
     *     possible object is
     *     {@link Informacije }
     *
     */
    public Informacije getInformacije() {
        return informacije;
    }

    /**
     * Sets the value of the informacije property.
     *
     * @param value
     *     allowed object is
     *     {@link Informacije }
     *
     */
    public void setInformacije(Informacije value) {
        this.informacije = value;
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
