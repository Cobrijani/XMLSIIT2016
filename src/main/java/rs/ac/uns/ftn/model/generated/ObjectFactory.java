
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

    private final static QName _PredmetIzmene_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", "predmet_izmene");
    private final static QName _TipIzmene_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", "tip_izmene");
    private final static QName _Amandman_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "amandman");
    private final static QName _Menja_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "menja");
    private final static QName _Preambula_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.akt", "preambula");
    private final static QName _Napravio_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "napravio");
    private final static QName _Korisnik_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "korisnik");
    private final static QName _Akt_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "akt");
    private final static QName _Pripada_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", "pripada");
    private final static QName _IzmenaUViseClanova_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", "izmena_u_vise_clanova");
    private final static QName _MalaIzmenaClanaNovaVrednost_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", "nova_vrednost");
    private final static QName _MalaIzmenaClanaStaraVrednost_QNAME = new QName("http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", "stara_vrednost");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.model.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZaglavljeAmandman }
     * 
     */
    public ZaglavljeAmandman createZaglavljeAmandman() {
        return new ZaglavljeAmandman();
    }

    /**
     * Create an instance of {@link Amandman }
     * 
     */
    public Amandman createAmandman() {
        return new Amandman();
    }

    /**
     * Create an instance of {@link ZaglavljeAmandman.AktRef }
     * 
     */
    public ZaglavljeAmandman.AktRef createZaglavljeAmandmanAktRef() {
        return new ZaglavljeAmandman.AktRef();
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
     * Create an instance of {@link Amandman.Izmene }
     * 
     */
    public Amandman.Izmene createAmandmanIzmene() {
        return new Amandman.Izmene();
    }

    /**
     * Create an instance of {@link Obrazlozenje }
     * 
     */
    public Obrazlozenje createObrazlozenje() {
        return new Obrazlozenje();
    }

    /**
     * Create an instance of {@link TReferenca }
     * 
     */
    public TReferenca createTReferenca() {
        return new TReferenca();
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link Podtacka }
     * 
     */
    public Podtacka createPodtacka() {
        return new Podtacka();
    }

    /**
     * Create an instance of {@link Referenca }
     * 
     */
    public Referenca createReferenca() {
        return new Referenca();
    }

    /**
     * Create an instance of {@link Alineja }
     * 
     */
    public Alineja createAlineja() {
        return new Alineja();
    }

    /**
     * Create an instance of {@link Clan }
     * 
     */
    public Clan createClan() {
        return new Clan();
    }

    /**
     * Create an instance of {@link Stav }
     * 
     */
    public Stav createStav() {
        return new Stav();
    }

    /**
     * Create an instance of {@link Tacka }
     * 
     */
    public Tacka createTacka() {
        return new Tacka();
    }

    /**
     * Create an instance of {@link MalaIzmenaClana }
     * 
     */
    public MalaIzmenaClana createMalaIzmenaClana() {
        return new MalaIzmenaClana();
    }

    /**
     * Create an instance of {@link IzmenaCelogClana }
     * 
     */
    public IzmenaCelogClana createIzmenaCelogClana() {
        return new IzmenaCelogClana();
    }

    /**
     * Create an instance of {@link Odeljak }
     * 
     */
    public Odeljak createOdeljak() {
        return new Odeljak();
    }

    /**
     * Create an instance of {@link Pododeljak }
     * 
     */
    public Pododeljak createPododeljak() {
        return new Pododeljak();
    }

    /**
     * Create an instance of {@link Akt }
     * 
     */
    public Akt createAkt() {
        return new Akt();
    }

    /**
     * Create an instance of {@link Zaglavlje }
     * 
     */
    public Zaglavlje createZaglavlje() {
        return new Zaglavlje();
    }

    /**
     * Create an instance of {@link Deo }
     * 
     */
    public Deo createDeo() {
        return new Deo();
    }

    /**
     * Create an instance of {@link Glava }
     * 
     */
    public Glava createGlava() {
        return new Glava();
    }

    /**
     * Create an instance of {@link Id }
     * 
     */
    public Id createId() {
        return new Id();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TReferenca }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", name = "predmet_izmene")
    public JAXBElement<TReferenca> createPredmetIzmene(TReferenca value) {
        return new JAXBElement<TReferenca>(_PredmetIzmene_QNAME, TReferenca.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TTipIzmene }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", name = "tip_izmene")
    public JAXBElement<TTipIzmene> createTipIzmene(TTipIzmene value) {
        return new JAXBElement<TTipIzmene>(_TipIzmene_QNAME, TTipIzmene.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.akt", name = "preambula")
    public JAXBElement<String> createPreambula(String value) {
        return new JAXBElement<String>(_Preambula_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.pred/", name = "akt")
    public JAXBElement<Object> createAkt(Object value) {
        return new JAXBElement<Object>(_Akt_QNAME, Object.class, null, value);
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
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", name = "izmena_u_vise_clanova")
    public JAXBElement<Object> createIzmenaUViseClanova(Object value) {
        return new JAXBElement<Object>(_IzmenaUViseClanova_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", name = "nova_vrednost", scope = MalaIzmenaClana.class)
    public JAXBElement<String> createMalaIzmenaClanaNovaVrednost(String value) {
        return new JAXBElement<String>(_MalaIzmenaClanaNovaVrednost_QNAME, String.class, MalaIzmenaClana.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman", name = "stara_vrednost", scope = MalaIzmenaClana.class)
    public JAXBElement<String> createMalaIzmenaClanaStaraVrednost(String value) {
        return new JAXBElement<String>(_MalaIzmenaClanaStaraVrednost_QNAME, String.class, MalaIzmenaClana.class, value);
    }

}
