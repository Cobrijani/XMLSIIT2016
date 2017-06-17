
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="for" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="against" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="notVote" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "results", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document")
public class Results {

    @XmlAttribute(name = "for")
    protected Integer _for;
    @XmlAttribute(name = "against")
    protected Integer against;
    @XmlAttribute(name = "notVote")
    protected Integer notVote;

    /**
     * Gets the value of the for property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getFor() {
        if (_for == null) {
            return  0;
        } else {
            return _for;
        }
    }

    /**
     * Sets the value of the for property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFor(Integer value) {
        this._for = value;
    }

    /**
     * Gets the value of the against property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getAgainst() {
        if (against == null) {
            return  0;
        } else {
            return against;
        }
    }

    /**
     * Sets the value of the against property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAgainst(Integer value) {
        this.against = value;
    }

    /**
     * Gets the value of the notVote property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getNotVote() {
        if (notVote == null) {
            return  0;
        } else {
            return notVote;
        }
    }

    /**
     * Sets the value of the notVote property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNotVote(Integer value) {
        this.notVote = value;
    }

}
