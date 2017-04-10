
package rs.ac.uns.ftn.model.generated;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="mesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "informacije", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Informacije {

    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar datum;
    @XmlAttribute(name = "mesto")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String mesto;

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:34:38+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMesto(String value) {
        this.mesto = value;
    }

}
