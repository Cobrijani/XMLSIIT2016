
package rs.ac.uns.ftn.model.korisnici;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for korisnickiDetalji complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
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
 *         &lt;element name="Email" type="{http://parlament.gov.rs/korisnici}email"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "korisnickiDetalji", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", propOrder = {
  "username",
  "password",
  "email"
})
public class KorisnickiDetalji {

  @XmlElement(name = "Username", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", required = true)
  protected String username;
  @XmlElement(name = "Password", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", required = true)
  protected String password;
  @XmlElement(name = "Email", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", required = true)
  protected String email;

  /**
   * Gets the value of the username property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the value of the username property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setUsername(String value) {
    this.username = value;
  }

  /**
   * Gets the value of the password property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the value of the password property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setPassword(String value) {
    this.password = value;
  }

  /**
   * Gets the value of the email property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the value of the email property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setEmail(String value) {
    this.email = value;
  }

}
