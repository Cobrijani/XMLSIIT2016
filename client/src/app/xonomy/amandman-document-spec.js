/**
 * Created by Arsa on 5/19/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .factory('AmandmanSpecification', AmandmanSpecification);

  AmandmanSpecification.$inject = ['$log', 'AmandmanTagsFactory', 'AktTagsFactory', 'MetaTagsFactory', 'RdfaTagsFactory', '$rootScope'];

  function AmandmanSpecification($log, AmandmanTagsFactory, AktTagsFactory, MetaTagsFactory, RdfaTagsFactory, $rootScope) {

    var amandmanSpec = {
      onchange: function () {
        $rootScope.$apply();
      },
      validate: function (jsElement) {
        var elementSpec = amandmanSpec.elements[jsElement.name];
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
            amandmanSpec.validate(child);
          }
        });

        if (Xonomy.warnings.length === 0) {
          $rootScope.$broadcast('validation:amandman', {
            valid: true, message: "Dokument je validan"
          });
        } else {
          $rootScope.$broadcast('validation:amandman', {
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

    function setAktReferences(aktrefs){
      var predmetIzmene = AmandmanTagsFactory.amandmanPredmetIzmene(aktrefs);
      var izmena = AmandmanTagsFactory.amandmanIzmena(aktrefs);
      var izmene = AmandmanTagsFactory.amandmanIzmene(aktrefs);

      registerElement(amandmanSpec, predmetIzmene);
      registerElement(amandmanSpec, izmena);
      registerElement(amandmanSpec, izmene);

    }

    var zaglavljeAmandman = AmandmanTagsFactory.amandmanZaglavljeAmandman();
    var resenja = AmandmanTagsFactory.amandmanResenja();
    var resenje = AmandmanTagsFactory.amandmanResenje();
    var obrazlozenje = AmandmanTagsFactory.amandmanObrazlozenje();
    var razlogPodnosenja = AmandmanTagsFactory.amandmanRazlogPodnosenja();
    var objasnjenjeResenja = AmandmanTagsFactory.amandmanObjasnjenjeResenja();
    var cilj = AmandmanTagsFactory.amandmanCilj();
    var procenaUticaja = AmandmanTagsFactory.amandmanProcenaUticaja();
    var documentAmRef = AmandmanTagsFactory.amandmanDocumentAmRef();


    var clan = AmandmanTagsFactory.aktClan();
    registerAttribute(clan, MetaTagsFactory.metaNazivAttr("Dodaj naziv člana", "Naziv člana"));
    registerAttribute(clan, MetaTagsFactory.metaIdAttr());

    var mNaziv = MetaTagsFactory.metaNaziv();
    registerAttribute(mNaziv, RdfaTagsFactory.rdfDataTypeAttr());
    registerAttribute(mNaziv, RdfaTagsFactory.rdfPropertyAttr("pred:imeDokumenta"));

    var tacka = AmandmanTagsFactory.aktTacka();
    registerAttribute(tacka, MetaTagsFactory.metaNazivAttr("Dodaj naziv tačke", "Naziv Tačke"));
    registerAttribute(tacka, MetaTagsFactory.metaIdAttr());

    var podtacka = AmandmanTagsFactory.aktPodtacka();
    registerAttribute(podtacka, MetaTagsFactory.metaNazivAttr("Dodaj naziv podtačke", "Naziv podtačke"));
    registerAttribute(podtacka, MetaTagsFactory.metaIdAttr());

    addChildActionsToParent(podtacka, AmandmanTagsFactory.aktAlineja());

    var stav = AmandmanTagsFactory.aktStav();
    registerAttribute(stav, MetaTagsFactory.metaIdAttr());


    var alineja = AmandmanTagsFactory.aktAlineja();
    registerAttribute(alineja, MetaTagsFactory.metaIdAttr());



    var referenca = AmandmanTagsFactory.aktReferenca();

    registerAttribute(referenca, MetaTagsFactory.metaIdRefAttr());
    addChildActionsToParent(stav, referenca);
    addChildActionsToParent(tacka, referenca);
    addChildActionsToParent(podtacka, referenca);
    addChildActionsToParent(alineja, referenca);

    registerElement(amandmanSpec, AmandmanTagsFactory.amandmanAmandman());
    registerElement(amandmanSpec, zaglavljeAmandman);

    registerElement(amandmanSpec, resenja);
    registerElement(amandmanSpec, resenje);

    registerElement(amandmanSpec, mNaziv);
    registerElement(amandmanSpec, stav);
    registerElement(amandmanSpec, tacka);
    registerElement(amandmanSpec, podtacka);
    registerElement(amandmanSpec, alineja);
    registerElement(amandmanSpec, referenca);
    registerElement(amandmanSpec, obrazlozenje);
    registerElement(amandmanSpec, razlogPodnosenja);
    registerElement(amandmanSpec, objasnjenjeResenja);
    registerElement(amandmanSpec, cilj);
    registerElement(amandmanSpec, procenaUticaja);
    registerElement(amandmanSpec, documentAmRef);

    return { amandman: amandmanSpec,
             setAktReferences: setAktReferences};
  }
})();




