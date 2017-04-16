/**
 * Created by SBratic on 3/8/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .factory('AktSpecification', AktSpecification);

  AktSpecification.$inject = ['$log', 'AktTagsFactory', 'MetaTagsFactory', 'RdfaTagsFactory', '$rootScope'];

  function AktSpecification($log, AktTagsFactory, MetaTagsFactory, RdfaTagsFactory, $rootScope) {
    var aktSpec = {
      onchange: function () {
        $rootScope.$apply();
      },
      validate: function (jsElement) {
        var elementSpec = aktSpec.elements[jsElement.name];
        //Validate the element:
        if (elementSpec.validate) {
          elementSpec.validate(jsElement);
        }
        //Cycle through the element's attributes:
        jsElement.attributes.forEach(function (attribute) {
          var attrSpec = elementSpec.attributes[attribute.name];

          if (attrSpec.validate) {
            attrSpec.validate(attribute);
          }
        });
        //Cycle through the element's children:
        jsElement.children.forEach(function (child) {
          if (child.type === "element") {
            aktSpec.validate(child);
          }
        });

        if (Xonomy.warnings.length === 0) {
          $rootScope.$broadcast('validation:akt', {
            valid: true, message: "Dokument je validan"
          });
        } else {
          $rootScope.$broadcast('validation:akt', {
            valid: false, message: "Dokument nije validan"
          });
        }

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

      if (!parent.definition.menu) {
        parent.definition.menu = [];
      }

      child.parentActions.forEach(function (item) {
        parent.definition.menu.push(item);
      });

      if (!child.parentInlineActions) {
        return;
      }

      if (!parent.definition.inlineMenu) {
        parent.definition.inlineMenu = [];
      }

      child.parentInlineActions.forEach(function (action) {
        parent.definition.inlineMenu.push(action);
      })
    }

    function registerAttribute(element, attribute) {
      if (!element.definition.attributes) {
        element.definition.attributes = {};
      }
      element.definition.attributes[attribute.name] = attribute.definition;
      addChildActionsToParent(element, attribute);
    }

    var deo = AktTagsFactory.aktDeo();
    registerAttribute(deo, MetaTagsFactory.metaNazivAttr("Dodaj naziv dela", "Naziv dela"));
    registerAttribute(deo, MetaTagsFactory.metaIdAttr());

    var clan = AktTagsFactory.aktClan();
    registerAttribute(clan, MetaTagsFactory.metaNazivAttr("Dodaj naziv člana", "Naziv člana"));
    registerAttribute(clan, MetaTagsFactory.metaIdAttr());

    var glava = AktTagsFactory.aktGlava();
    registerAttribute(glava, MetaTagsFactory.metaNazivAttr("Dodaj naziv glave", "Naziv Glave"));
    registerAttribute(glava, MetaTagsFactory.metaIdAttr());

    var odeljak = AktTagsFactory.aktOdeljak();
    registerAttribute(odeljak, MetaTagsFactory.metaNazivAttr("Dodaj naziv odeljka", "Naziv odeljka"));
    registerAttribute(odeljak, MetaTagsFactory.metaIdAttr());

    var mNaziv = MetaTagsFactory.metaNaziv();
    registerAttribute(mNaziv, RdfaTagsFactory.rdfDataTypeAttr());
    registerAttribute(mNaziv, RdfaTagsFactory.rdfPropertyAttr("pred:imeDokumenta"));

    var tacka = AktTagsFactory.aktTacka();
    registerAttribute(tacka, MetaTagsFactory.metaNazivAttr("Dodaj naziv tačke", "Naziv Tačke"));
    registerAttribute(tacka, MetaTagsFactory.metaIdAttr());

    var podtacka = AktTagsFactory.aktPodtacka();
    registerAttribute(podtacka, MetaTagsFactory.metaNazivAttr("Dodaj naziv podtačke", "Naziv podtačke"));
    registerAttribute(podtacka, MetaTagsFactory.metaIdAttr());

    addChildActionsToParent(podtacka, AktTagsFactory.aktAlineja());

    var stav = AktTagsFactory.aktStav();
    registerAttribute(stav, MetaTagsFactory.metaIdAttr());

    var pododeljak = AktTagsFactory.aktPododeljak();
    registerAttribute(pododeljak, MetaTagsFactory.metaNazivAttr("Dodaj naziv pododeljka", "Naziv pododeljka"));
    registerAttribute(pododeljak, MetaTagsFactory.metaIdAttr());

    var alineja = AktTagsFactory.aktAlineja();
    registerAttribute(alineja, MetaTagsFactory.metaIdAttr());

    var zaglavlje = AktTagsFactory.aktZaglavlje();
    zaglavlje.definition.mustBeBefore = [clan.name, glava.name, deo.name];

    var preambula = AktTagsFactory.aktPreambula();

    var referenca = AktTagsFactory.aktReferenca();
    registerAttribute(referenca, MetaTagsFactory.metaIdRefAttr());
    addChildActionsToParent(stav, referenca);
    addChildActionsToParent(tacka, referenca);
    addChildActionsToParent(podtacka, referenca);
    addChildActionsToParent(alineja, referenca);

    registerElement(aktSpec, AktTagsFactory.aktAkt());
    registerElement(aktSpec, zaglavlje);
    registerElement(aktSpec, preambula);
    registerElement(aktSpec, glava);
    registerElement(aktSpec, clan);
    registerElement(aktSpec, deo);
    registerElement(aktSpec, mNaziv);
    registerElement(aktSpec, odeljak);
    registerElement(aktSpec, stav);
    registerElement(aktSpec, tacka);
    registerElement(aktSpec, pododeljak);
    registerElement(aktSpec, podtacka);
    registerElement(aktSpec, alineja);
    registerElement(aktSpec, referenca);

    return { akt: aktSpec };
  }
})();




