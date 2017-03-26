
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}podtacka"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}alineja"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}clan"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}stav"/>
 *         &lt;element ref="{http://parlament.gov.rs/rs.ac.uns.ftn.model.akt}tacka"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podtacka",
    "alineja",
    "clan",
    "stav",
    "tacka"
})
@XmlRootElement(name = "resenje", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class Resenje {

    @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    protected Podtacka podtacka;
  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    protected Alineja alineja;
  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    protected Clan clan;
  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    protected Stav stav;
  @XmlElement(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt")
    protected Tacka tacka;

    /**
     * Gets the value of the podtacka property.
     *
     * @return
     *     possible object is
     *     {@link Podtacka }
     *
     */
    public Podtacka getPodtacka() {
        return podtacka;
    }

    /**
     * Sets the value of the podtacka property.
     *
     * @param value
     *     allowed object is
     *     {@link Podtacka }
     *
     */
    public void setPodtacka(Podtacka value) {
        this.podtacka = value;
    }

    /**
     * Gets the value of the alineja property.
     *
     * @return
     *     possible object is
     *     {@link Alineja }
     *
     */
    public Alineja getAlineja() {
        return alineja;
    }

    /**
     * Sets the value of the alineja property.
     *
     * @param value
     *     allowed object is
     *     {@link Alineja }
     *
     */
    public void setAlineja(Alineja value) {
        this.alineja = value;
    }

    /**
     * Gets the value of the clan property.
     *
     * @return
     *     possible object is
     *     {@link Clan }
     *
     */
    public Clan getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     *
     * @param value
     *     allowed object is
     *     {@link Clan }
     *
     */
    public void setClan(Clan value) {
        this.clan = value;
    }

    /**
     * Gets the value of the stav property.
     *
     * @return
     *     possible object is
     *     {@link Stav }
     *
     */
    public Stav getStav() {
        return stav;
    }

    /**
     * Sets the value of the stav property.
     *
     * @param value
     *     allowed object is
     *     {@link Stav }
     *
     */
    public void setStav(Stav value) {
        this.stav = value;
    }

    /**
     * Gets the value of the tacka property.
     *
     * @return
     *     possible object is
     *     {@link Tacka }
     *
     */
    public Tacka getTacka() {
        return tacka;
    }

    /**
     * Sets the value of the tacka property.
     *
     * @param value
     *     allowed object is
     *     {@link Tacka }
     *
     */
    public void setTacka(Tacka value) {
        this.tacka = value;
    }

}
