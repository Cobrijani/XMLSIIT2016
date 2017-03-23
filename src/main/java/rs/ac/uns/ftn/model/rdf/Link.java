
package rs.ac.uns.ftn.model.rdf;

import javax.annotation.Generated;
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
 *       &lt;attGroup ref="{http://www.w3.org/2007/SPARQL/results#}hrefAttr"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "link")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Link {

    @XmlAttribute(name = "href", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String href;

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHref(String value) {
        this.href = value;
    }

}
