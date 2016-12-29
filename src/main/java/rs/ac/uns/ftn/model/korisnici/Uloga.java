
package rs.ac.uns.ftn.model.korisnici;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uloga.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="uloga">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gradjanin"/>
 *     &lt;enumeration value="odbornik"/>
 *     &lt;enumeration value="predsednik"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "uloga", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici")
@XmlEnum
public enum Uloga {

  @XmlEnumValue("gradjanin")
  GRADJANIN("gradjanin"),
  @XmlEnumValue("odbornik")
  ODBORNIK("odbornik"),
  @XmlEnumValue("predsednik")
  PREDSEDNIK("predsednik");
  private final String value;

  Uloga(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static Uloga fromValue(String v) {
    for (Uloga c : Uloga.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
