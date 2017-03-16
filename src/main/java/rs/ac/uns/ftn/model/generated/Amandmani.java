
package rs.ac.uns.ftn.model.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


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
 *         &lt;element name="amandman_ref" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;anyAttribute/>
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
    "amandmanRef"
})
@XmlRootElement(name = "amandmani", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Amandmani {

    @XmlElement(name = "amandman_ref", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.sednica")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Amandmani.AmandmanRef> amandmanRef;

    /**
     * Gets the value of the amandmanRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amandmanRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmandmanRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Amandmani.AmandmanRef }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Amandmani.AmandmanRef> getAmandmanRef() {
        if (amandmanRef == null) {
            amandmanRef = new ArrayList<Amandmani.AmandmanRef>();
        }
        return this.amandmanRef;
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
     *       &lt;anyAttribute/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class AmandmanRef {

        @XmlAnyAttribute
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-16T07:39:56+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
