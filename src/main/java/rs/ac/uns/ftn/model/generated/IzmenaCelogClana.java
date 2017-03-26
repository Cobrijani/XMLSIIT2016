
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}tip_izmene"/>
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
    "predmetIzmene",
    "tipIzmene",
    "resenje"
})
@XmlRootElement(name = "izmena_celog_clana", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class IzmenaCelogClana {

    @XmlElement(name = "predmet_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected TReferenca predmetIzmene;
    @XmlElement(name = "tip_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    protected TTipIzmene tipIzmene;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
    protected List<Resenje> resenje;

    /**
     * Gets the value of the predmetIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link TReferenca }
     *     
     */
    public TReferenca getPredmetIzmene() {
        return predmetIzmene;
    }

    /**
     * Sets the value of the predmetIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link TReferenca }
     *     
     */
    public void setPredmetIzmene(TReferenca value) {
        this.predmetIzmene = value;
    }

    /**
     * Gets the value of the tipIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link TTipIzmene }
     *     
     */
    public TTipIzmene getTipIzmene() {
        return tipIzmene;
    }

    /**
     * Sets the value of the tipIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTipIzmene }
     *     
     */
    public void setTipIzmene(TTipIzmene value) {
        this.tipIzmene = value;
    }

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
