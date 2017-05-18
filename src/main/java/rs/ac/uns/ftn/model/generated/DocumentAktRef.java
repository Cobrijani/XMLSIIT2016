
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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.document}document"/>
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
    "document"
})
@XmlRootElement(name = "document_akt_ref", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DocumentAktRef {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document", required = true, nillable = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Document document;

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Document getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-03-20T10:36:57+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDocument(Document value) {
        this.document = value;
    }

}
