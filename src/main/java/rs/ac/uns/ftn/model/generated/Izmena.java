
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}predmet_izmene"/>
 *         &lt;element name="resenja">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}resenje" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "predmetIzmene",
    "resenja"
})
@XmlRootElement(name = "izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class Izmena {

    @XmlElement(name = "predmet_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected PredmetIzmene predmetIzmene;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected Izmena.Resenja resenja;

    /**
     * Gets the value of the predmetIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link PredmetIzmene }
     *     
     */
    public PredmetIzmene getPredmetIzmene() {
        return predmetIzmene;
    }

    /**
     * Sets the value of the predmetIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredmetIzmene }
     *     
     */
    public void setPredmetIzmene(PredmetIzmene value) {
        this.predmetIzmene = value;
    }

    /**
     * Gets the value of the resenja property.
     * 
     * @return
     *     possible object is
     *     {@link Izmena.Resenja }
     *     
     */
    public Izmena.Resenja getResenja() {
        return resenja;
    }

    /**
     * Sets the value of the resenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izmena.Resenja }
     *     
     */
    public void setResenja(Izmena.Resenja value) {
        this.resenja = value;
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
     *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}resenje" maxOccurs="unbounded" minOccurs="0"/>
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
        "resenje"
    })
    public static class Resenja {

        @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
        protected List<Resenje> resenje;

        /**
         * Gets the value of the resenje property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resenje property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResenje().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Resenje }
         * 
         * 
         */
        public List<Resenje> getResenje() {
            if (resenje == null) {
                resenje = new ArrayList<Resenje>();
            }
            return this.resenje;
        }

    }

}
