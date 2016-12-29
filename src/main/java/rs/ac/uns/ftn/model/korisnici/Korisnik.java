
package rs.ac.uns.ftn.model.korisnici;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uloga" type="{http://parlament.gov.rs/korisnici}uloga"/>
 *         &lt;element name="korisnickiDetalji" type="{http://parlament.gov.rs/korisnici}korisnickiDetalji"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "uloga",
  "korisnickiDetalji"
})
@XmlRootElement(name = "korisnik", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici")
public class Korisnik {

  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", required = true)
  @XmlSchemaType(name = "string")
  protected Uloga uloga;
  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici", required = true)
  protected KorisnickiDetalji korisnickiDetalji;
  @XmlAttribute(name = "id")
  @XmlSchemaType(name = "anySimpleType")
  protected String id;

  /**
   * Gets the value of the uloga property.
   *
   * @return possible object is
   * {@link Uloga }
   */
  public Uloga getUloga() {
    return uloga;
  }

  /**
   * Sets the value of the uloga property.
   *
   * @param value allowed object is
   *              {@link Uloga }
   */
  public void setUloga(Uloga value) {
    this.uloga = value;
  }

  /**
   * Gets the value of the korisnickiDetalji property.
   *
   * @return possible object is
   * {@link KorisnickiDetalji }
   */
  public KorisnickiDetalji getKorisnickiDetalji() {
    return korisnickiDetalji;
  }

  /**
   * Sets the value of the korisnickiDetalji property.
   *
   * @param value allowed object is
   *              {@link KorisnickiDetalji }
   */
  public void setKorisnickiDetalji(KorisnickiDetalji value) {
    this.korisnickiDetalji = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setId(String value) {
    this.id = value;
  }

}
