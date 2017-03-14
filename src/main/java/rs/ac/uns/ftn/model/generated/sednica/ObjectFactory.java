
package rs.ac.uns.ftn.model.generated.sednica;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the xml package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Napravio_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "napravio");
    private final static QName _Korisnik_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "korisnik");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sednica }
     *
     */
    public Sednica createSednica() {
        return new Sednica();
    }

    /**
     * Create an instance of {@link Zaglavlje }
     *
     */
    public Zaglavlje createZaglavlje() {
        return new Zaglavlje();
    }

    /**
     * Create an instance of {@link Naziv }
     *
     */
    public Naziv createNaziv() {
        return new Naziv();
    }

    /**
     * Create an instance of {@link DateCreated }
     *
     */
    public DateCreated createDateCreated() {
        return new DateCreated();
    }

    /**
     * Create an instance of {@link DateModified }
     *
     */
    public DateModified createDateModified() {
        return new DateModified();
    }

    /**
     * Create an instance of {@link Informacije }
     *
     */
    public Informacije createInformacije() {
        return new Informacije();
    }

    /**
     * Create an instance of {@link Id }
     *
     */
    public Id createId() {
        return new Id();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "napravio")
    public JAXBElement<Object> createNapravio(Object value) {
        return new JAXBElement<Object>(_Napravio_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "korisnik")
    public JAXBElement<Object> createKorisnik(Object value) {
        return new JAXBElement<Object>(_Korisnik_QNAME, Object.class, null, value);
    }

}
