/**
 * Created by SBratic on 3/8/2017.
 */

(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('AktSpecification', AktSpecification);

  AktSpecification.$inject = ['$log'];

  function AktSpecification($log) {
    var aktSpec = {
      onchange: function (obj) {
        $log.info('onchange: ', obj);
      },
      validate: function (obj) {
        $log.info('validate: ', obj);
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

    var deo = aktDeo();
    registerAttribute(deo, metaNazivAttr("Dodaj naziv dela", "Naziv dela"));
    var clan = aktClan();
    registerAttribute(clan, metaNazivAttr("Dodaj naziv člana", "Naziv člana"));
    var glava = aktGlava();
    registerAttribute(glava, metaNazivAttr("Dodaj naziv glave", "Naziv Glave"));
    var odeljak = aktOdeljak();
    registerAttribute(odeljak, metaNazivAttr("Dodaj naziv odeljka", "Naziv odeljka"));
    var mNaziv = metaNaziv();
    registerAttribute(mNaziv, rdfDataTypeAttr());
    registerAttribute(mNaziv, rdfPropertyAttr("pred:imeDokumenta"));
    var tacka = aktTacka();
    registerAttribute(tacka, metaNazivAttr("Dodaj naziv tačke", "Naziv Tačke"));
    var podtacka = aktPodtacka();
    registerAttribute(podtacka, metaNazivAttr("Dodaj naziv podtačke", "Naziv podtačke"));
    addChildActionsToParent(podtacka, aktAlineja());
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

    return {akt: aktSpec};
  }
})();




