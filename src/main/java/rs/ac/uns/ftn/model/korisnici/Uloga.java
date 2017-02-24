
package rs.ac.uns.ftn.model.korisnici;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uloga.
 * 
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
 * 
 */
@XmlType(name = "uloga")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2017-02-24T03:51:36+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
        for (Uloga c: Uloga.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
