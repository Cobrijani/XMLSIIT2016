/**
 * Created by SBratic on 3/8/2017.
 */


var aktSpec = {
  onchange: function (obj) {
    //console.log("Changing ", obj);
  },
  validate: function (obj) {
    //console.log('Validation: ', obj);
  },
  elements: {}

};

function registerElement(spec, element) {
  spec.elements[element.name] = element.definition;
}

function addChildActionsToParent(parent, child) {
  if (!child.parentActions) {
    return;
  }

  child.parentActions.forEach(function (item) {
    parent.definition.menu.push(item)
  })
}

function registerAttribute(element, attribute) {
  element.definition.attributes[attribute.name] = attribute.definition;
  addChildActionsToParent(element, attribute);
}

//DEO
var deo = aktDeo();
registerAttribute(deo, metaNazivAttr("Dodaj naziv dela", "Naziv dela"));
//DEO

//clan
var clan = aktClan();
registerAttribute(clan, metaNazivAttr("Dodaj naziv člana", "Naziv člana"));
//clan
//glava
var glava = aktGlava();
registerAttribute(glava, metaNazivAttr("Dodaj naziv glave", "Naziv Glave"));
//glava

//odeljak
var odeljak = aktOdeljak();
registerAttribute(odeljak, metaNazivAttr("Dodaj naziv odeljka", "Naziv odeljka"));
//odeljak

//meta:naziv
var mNaziv = metaNaziv();
registerAttribute(mNaziv, rdfDataTypeAttr());
registerAttribute(mNaziv, rdfPropertyAttr("pred:imeDokumenta"));
//meta:naziv

//akt:tacka
var tacka = aktTacka();
registerAttribute(tacka, metaNazivAttr("Dodaj naziv tačke", "Naziv Tačke"));

//akt:tacka

//akt:podtacka
var podtacka = aktPodtacka();
registerAttribute(podtacka, metaNazivAttr("Dodaj naziv podtačke", "Naziv podtačke"));
addChildActionsToParent(podtacka, aktAlineja());
//akt:podtacka


registerElement(aktSpec, aktAkt());
registerElement(aktSpec, aktZaglavlje());
registerElement(aktSpec, aktPreambula());
registerElement(aktSpec, glava);
registerElement(aktSpec, clan);
registerElement(aktSpec, deo);
registerElement(aktSpec, mNaziv);
registerElement(aktSpec, odeljak);
registerElement(aktSpec, aktStav());
registerElement(aktSpec, tacka);
registerElement(aktSpec, aktPododeljak());
registerElement(aktSpec, podtacka);
registerElement(aktSpec, aktAlineja());

console.log(aktSpec.elements);
