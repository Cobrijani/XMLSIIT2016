
package rs.ac.uns.ftn.model.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
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
 *         &lt;element name="stara_vrednost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}tip_izmene"/>
 *         &lt;element name="nova_vrednost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "content"
})
@XmlRootElement(name = "mala_izmena_clana", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class MalaIzmenaClana {

    @XmlElementRefs({
        @XmlElementRef(name = "nova_vrednost", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = JAXBElement.class),
        @XmlElementRef(name = "tip_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = JAXBElement.class),
        @XmlElementRef(name = "predmet_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = JAXBElement.class),
        @XmlElementRef(name = "stara_vrednost", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TTipIzmene }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TReferenca }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
