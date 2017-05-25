
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman>TReferenca">
 *       &lt;attribute name="tip_izmene" type="{http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman}TTipIzmene" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "predmet_izmene", namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman")
public class PredmetIzmene
    extends TReferenca
{

    @XmlAttribute(name = "tip_izmene")
    protected TTipIzmene tipIzmene;

    /**
     * Gets the value of the tipIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link TTipIzmene }
     *     
     */
    public TTipIzmene getTipIzmene() {
        return tipIzmene;
    }

    /**
     * Sets the value of the tipIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTipIzmene }
     *     
     */
    public void setTipIzmene(TTipIzmene value) {
        this.tipIzmene = value;
    }

}
