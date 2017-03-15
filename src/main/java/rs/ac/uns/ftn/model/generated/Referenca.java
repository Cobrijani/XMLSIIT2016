
package rs.ac.uns.ftn.model.generated;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}idRef"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "referenca", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Referenca {

    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String value;
    @XmlAttribute(name = "idRef", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String idRef;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the idRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getIdRef() {
        return idRef;
    }

    /**
     * Sets the value of the idRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-15T10:09:12+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIdRef(String value) {
        this.idRef = value;
    }

}
