
package rs.ac.uns.ftn.model.korisnici;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for korisnickiDetalji complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="korisnickiDetalji">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Username">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Password">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="6"/>
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Email" type="{http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici}email"/>
 *         &lt;element name="Firstname">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="200"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Lastname">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="200"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "korisnickiDetalji", propOrder = {
    "username",
    "password",
    "email",
    "firstname",
    "lastname"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class KorisnickiDetalji {

    @XmlElement(name = "Username", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String username;
    @XmlElement(name = "Password", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String password;
    @XmlElement(name = "Email", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String email;
    @XmlElement(name = "Firstname", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String firstname;
    @XmlElement(name = "Lastname", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String lastname;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLastname(String value) {
        this.lastname = value;
    }

}
