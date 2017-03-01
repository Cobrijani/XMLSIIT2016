
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}referenca" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}potacka" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}uobicajeniAtributi"/>
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
@XmlRootElement(name = "tacka")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Tacka {

    @XmlElementRefs({
        @XmlElementRef(name = "referenca", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt", type = Referenca.class, required = false),
        @XmlElementRef(name = "potacka", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt", type = Potacka.class, required = false)
    })
    @XmlMixed
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Object> content;
    @XmlAttribute(name = "naziv", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String naziv;
    @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

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
     * {@link Referenca }
     * {@link Potacka }
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-01T04:39:54+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }

}
