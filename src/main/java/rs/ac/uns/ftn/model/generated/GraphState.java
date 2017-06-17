
package rs.ac.uns.ftn.model.generated;

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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}aktState"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata}aktVersion"/>
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
    "aktState",
    "aktVersion"
})
@XmlRootElement(name = "graphState", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.document")
public class GraphState {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata", required = true)
    protected AktState aktState;
    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata", required = true)
    protected AktVersion aktVersion;

    /**
     * Gets the value of the aktState property.
     * 
     * @return
     *     possible object is
     *     {@link AktState }
     *     
     */
    public AktState getAktState() {
        return aktState;
    }

    /**
     * Sets the value of the aktState property.
     * 
     * @param value
     *     allowed object is
     *     {@link AktState }
     *     
     */
    public void setAktState(AktState value) {
        this.aktState = value;
    }

    /**
     * Gets the value of the aktVersion property.
     * 
     * @return
     *     possible object is
     *     {@link AktVersion }
     *     
     */
    public AktVersion getAktVersion() {
        return aktVersion;
    }

    /**
     * Sets the value of the aktVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AktVersion }
     *     
     */
    public void setAktVersion(AktVersion value) {
        this.aktVersion = value;
    }

}
