
package rs.ac.uns.ftn.model.generated;

import javax.annotation.Generated;
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}odredba"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}tip_izmene"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}resenje"/>
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
    "odredba",
    "tipIzmene",
    "resenje"
})
@XmlRootElement(name = "izmena", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Izmena {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Odredba odredba;
    @XmlElement(name = "tip_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String tipIzmene;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Resenje resenje;

    /**
     * Gets the value of the odredba property.
     * 
     * @return
     *     possible object is
     *     {@link Odredba }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Odredba getOdredba() {
        return odredba;
    }

    /**
     * Sets the value of the odredba property.
     * 
     * @param value
     *     allowed object is
     *     {@link Odredba }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOdredba(Odredba value) {
        this.odredba = value;
    }

    /**
     * Gets the value of the tipIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTipIzmene() {
        return tipIzmene;
    }

    /**
     * Sets the value of the tipIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTipIzmene(String value) {
        this.tipIzmene = value;
    }

    /**
     * Gets the value of the resenje property.
     * 
     * @return
     *     possible object is
     *     {@link Resenje }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Resenje getResenje() {
        return resenje;
    }

    /**
     * Sets the value of the resenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resenje }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setResenje(Resenje value) {
        this.resenje = value;
    }

}
