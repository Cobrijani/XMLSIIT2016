
package rs.ac.uns.ftn.model.generated;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>dateTime">
 *       &lt;anyAttribute processContents='lax'/>
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
@XmlRootElement(name = "dateModified", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DateModified {

    @XmlValue
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar value;
    @XmlAnyAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setValue(XMLGregorianCalendar value) {
        this.value = value;
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T06:00:42+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
