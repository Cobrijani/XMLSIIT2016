
package rs.ac.uns.ftn.model.rdf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}binding" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="index" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "binding"
})
@XmlRootElement(name = "result")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Result {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Binding> binding;
    @XmlAttribute(name = "index")
    @XmlSchemaType(name = "positiveInteger")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger index;

    /**
     * Gets the value of the binding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Binding }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Binding> getBinding() {
        if (binding == null) {
            binding = new ArrayList<Binding>();
        }
        return this.binding;
    }

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIndex(BigInteger value) {
        this.index = value;
    }

}
