
package rs.ac.uns.ftn.model.rdf;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}uri"/>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}bnode"/>
 *         &lt;element ref="{http://www.w3.org/2007/SPARQL/results#}literal"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.w3.org/2007/SPARQL/results#}nameAttr"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uri",
    "bnode",
    "literal"
})
@XmlRootElement(name = "binding")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Binding {

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String uri;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String bnode;
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Literal literal;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String name;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the bnode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getBnode() {
        return bnode;
    }

    /**
     * Sets the value of the bnode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setBnode(String value) {
        this.bnode = value;
    }

    /**
     * Gets the value of the literal property.
     * 
     * @return
     *     possible object is
     *     {@link Literal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Literal getLiteral() {
        return literal;
    }

    /**
     * Sets the value of the literal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Literal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLiteral(Literal value) {
        this.literal = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setName(String value) {
        this.name = value;
    }

}
