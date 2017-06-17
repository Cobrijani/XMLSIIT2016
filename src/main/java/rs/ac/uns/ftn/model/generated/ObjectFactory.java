
package rs.ac.uns.ftn.model.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.model.generated package. 
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

    private final static QName _Glasao_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "glasao");
    private final static QName _Amandman_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "amandman");
    private final static QName _Menja_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "menja");
    private final static QName _Napravio_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "napravio");
    private final static QName _Korisnik_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "korisnik");
    private final static QName _Pripada_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "pripada");
    private final static QName _Akt_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "akt");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.model.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Akti }
     * 
     */
    public Akti createAkti() {
        return new Akti();
    }

    /**
     * Create an instance of {@link Amandmani }
     * 
     */
    public Amandmani createAmandmani() {
        return new Amandmani();
    }

    /**
     * Create an instance of {@link Sednica }
     * 
     */
    public Sednica createSednica() {
        return new Sednica();
    }

    /**
     * Create an instance of {@link ZaglavljeSednica }
     * 
     */
    public ZaglavljeSednica createZaglavljeSednica() {
        return new ZaglavljeSednica();
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
     * Create an instance of {@link Akti.AktRef }
     * 
     */
    public Akti.AktRef createAktiAktRef() {
        return new Akti.AktRef();
    }

    /**
     * Create an instance of {@link Amandmani.AmandmanRef }
     * 
     */
    public Amandmani.AmandmanRef createAmandmaniAmandmanRef() {
        return new Amandmani.AmandmanRef();
    }

    /**
     * Create an instance of {@link AktState }
     * 
     */
    public AktState createAktState() {
        return new AktState();
    }

    /**
     * Create an instance of {@link AktVersion }
     * 
     */
    public AktVersion createAktVersion() {
        return new AktVersion();
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
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "glasao")
    public JAXBElement<Object> createGlasao(Object value) {
        return new JAXBElement<Object>(_Glasao_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "amandman")
    public JAXBElement<Object> createAmandman(Object value) {
        return new JAXBElement<Object>(_Amandman_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "menja")
    public JAXBElement<Object> createMenja(Object value) {
        return new JAXBElement<Object>(_Menja_QNAME, Object.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "pripada")
    public JAXBElement<Object> createPripada(Object value) {
        return new JAXBElement<Object>(_Pripada_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "akt")
    public JAXBElement<Object> createAkt(Object value) {
        return new JAXBElement<Object>(_Akt_QNAME, Object.class, null, value);
    }

}
