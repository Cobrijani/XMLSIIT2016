
package rs.ac.uns.ftn.model.akt;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sadrzaj">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://parlament.gov.rs/akt}stav" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://parlament.gov.rs/akt}prosireniUobicajeniAtributi"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "opis",
  "sadrzaj"
})
@XmlRootElement(name = "clan")
public class Clan {

  protected String opis;
  @XmlElement(required = true)
  protected Clan.Sadrzaj sadrzaj;
  @XmlAttribute(name = "redniBroj")
  protected String redniBroj;
  @XmlAttribute(name = "naziv")
  protected String naziv;
  @XmlAttribute(name = "id", namespace = "http://parlament.gov.rs/akt")
  protected String id;

  /**
   * Gets the value of the opis property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getOpis() {
    return opis;
  }

  /**
   * Sets the value of the opis property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setOpis(String value) {
    this.opis = value;
  }

  /**
   * Gets the value of the sadrzaj property.
   *
   * @return possible object is
   * {@link Clan.Sadrzaj }
   */
  public Clan.Sadrzaj getSadrzaj() {
    return sadrzaj;
  }

  /**
   * Sets the value of the sadrzaj property.
   *
   * @param value allowed object is
   *              {@link Clan.Sadrzaj }
   */
  public void setSadrzaj(Clan.Sadrzaj value) {
    this.sadrzaj = value;
  }

  /**
   * Gets the value of the redniBroj property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getRedniBroj() {
    return redniBroj;
  }

  /**
   * Sets the value of the redniBroj property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setRedniBroj(String value) {
    this.redniBroj = value;
  }

  /**
   * Gets the value of the naziv property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getNaziv() {
    return naziv;
  }

  /**
   * Sets the value of the naziv property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setNaziv(String value) {
    this.naziv = value;
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
   *         &lt;element ref="{http://parlament.gov.rs/akt}stav" maxOccurs="unbounded"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
    "stav"
  })
  public static class Sadrzaj {

    @XmlElement(required = true)
    protected List<Stav> stav;

    /**
     * Gets the value of the stav property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stav property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStav().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Stav }
     */
    public List<Stav> getStav() {
      if (stav == null) {
        stav = new ArrayList<Stav>();
      }
      return this.stav;
    }

  }

}
