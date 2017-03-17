
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}zaglavlje_amandman"/>
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
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}id"/>
 *       &lt;attribute name="akt_id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "zaglavljeAmandman",
    "izmene",
    "obrazlozenje"
})
@XmlRootElement(name = "amandman", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class Amandman {

    @XmlElement(name = "zaglavlje_amandman", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected ZaglavljeAmandman zaglavljeAmandman;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected Amandman.Izmene izmene;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected Obrazlozenje obrazlozenje;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    protected String id;
    @XmlAttribute(name = "akt_id")
    protected String aktId;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the zaglavljeAmandman property.
     * 
     * @return
     *     possible object is
     *     {@link ZaglavljeAmandman }
     *     
     */
    public ZaglavljeAmandman getZaglavljeAmandman() {
        return zaglavljeAmandman;
    }

    /**
     * Sets the value of the zaglavljeAmandman property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaglavljeAmandman }
     *     
     */
    public void setZaglavljeAmandman(ZaglavljeAmandman value) {
        this.zaglavljeAmandman = value;
    }

    /**
     * Gets the value of the izmene property.
     * 
     * @return
     *     possible object is
     *     {@link Amandman.Izmene }
     *     
     */
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
    public void setObrazlozenje(Obrazlozenje value) {
        this.obrazlozenje = value;
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
     * Gets the value of the aktId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAktId() {
        return aktId;
    }

    /**
     * Sets the value of the aktId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAktId(String value) {
        this.aktId = value;
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
    public static class Izmene {

        @XmlElements({
            @XmlElement(name = "izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = Izmena.class),
            @XmlElement(name = "mala_izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = MalaIzmena.class)
        })
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
        public List<Object> getIzmenaOrMalaIzmena() {
            if (izmenaOrMalaIzmena == null) {
                izmenaOrMalaIzmena = new ArrayList<Object>();
            }
            return this.izmenaOrMalaIzmena;
        }

    }

}
