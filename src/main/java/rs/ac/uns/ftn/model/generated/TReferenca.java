
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TReferenca complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TReferenca">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}AGReferenca"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReferenca", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", propOrder = {
    "value"
})
public class TReferenca {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "ceo_akt")
    protected Boolean ceoAkt;
    @XmlAttribute(name = "ref_clanovi")
    protected String refClanovi;
    @XmlAttribute(name = "ref_stavovi")
    protected String refStavovi;
    @XmlAttribute(name = "ref_tacke")
    protected String refTacke;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the ceoAkt property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCeoAkt() {
        if (ceoAkt == null) {
            return false;
        } else {
            return ceoAkt;
        }
    }

    /**
     * Sets the value of the ceoAkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCeoAkt(Boolean value) {
        this.ceoAkt = value;
    }

    /**
     * Gets the value of the refClanovi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefClanovi() {
        return refClanovi;
    }

    /**
     * Sets the value of the refClanovi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefClanovi(String value) {
        this.refClanovi = value;
    }

    /**
     * Gets the value of the refStavovi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefStavovi() {
        return refStavovi;
    }

    /**
     * Sets the value of the refStavovi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefStavovi(String value) {
        this.refStavovi = value;
    }

    /**
     * Gets the value of the refTacke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefTacke() {
        return refTacke;
    }

    /**
     * Sets the value of the refTacke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefTacke(String value) {
        this.refTacke = value;
    }

}
