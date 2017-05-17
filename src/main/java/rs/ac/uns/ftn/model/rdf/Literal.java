
package rs.ac.uns.ftn.model.rdf;

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
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="datatype" type="{http://www.w3.org/2007/SPARQL/results#}URI-reference" />
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
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
@XmlRootElement(name = "literal")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Literal {

    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String content;
    @XmlAttribute(name = "datatype")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String datatype;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String lang;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the datatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatatype(String value) {
        this.datatype = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-21T09:55:15+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLang(String value) {
        this.lang = value;
    }

}
