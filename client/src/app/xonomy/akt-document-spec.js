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

function registerAttribute(element, attribute) {
  element.definition.attributes[attribute.name] = attribute.definition;

  if (!attribute.parentActions) {
    return;
  }

  attribute.parentActions.forEach(function (item) {
    element.definition.menu.push(item);
  });
}

//DEO
var deo = aktDeo();
registerAttribute(deo, metaNazivAttr());
//DEO

//clan
var clan = aktClan();
registerAttribute(clan, metaNazivAttr());
//clan
//glava
var glava = aktGlava();
registerAttribute(glava, metaNazivAttr());
//glava

//odeljak
var odeljak = aktOdeljak();
registerAttribute(odeljak, metaNazivAttr());
//odeljak

//meta:naziv
var mNaziv = metaNaziv();
registerAttribute(mNaziv, rdfDataTypeAttr());
registerAttribute(mNaziv, rdfPropertyAttr("pred:imeDokumenta"));
//meta:naziv

registerElement(aktSpec, aktAkt());
registerElement(aktSpec, aktZaglavlje());
registerElement(aktSpec, aktPreambula());
registerElement(aktSpec, glava);
registerElement(aktSpec, clan);
registerElement(aktSpec, deo);
registerElement(aktSpec, mNaziv);
registerElement(aktSpec, odeljak);
registerElement(aktSpec, aktStav());
registerElement(aktSpec, aktTacka());
registerElement(aktSpec, aktPododeljak());


console.log(aktSpec.elements);
