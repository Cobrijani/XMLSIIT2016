/**
 * This file contains definitions for Xonomy for each tag for akt.xsd
 * Created by SBratic on 3/9/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .factory('AktTagsFactory', AktTagsFactory);

  AktTagsFactory.$inject = ['MetaTagsFactory', 'namespaces'];

  function AktTagsFactory(MetaTagsFactory, namespaces) {

    return {
      aktAkt: aktAkt,
      aktZaglavlje: aktZaglavlje,
      aktPreambula: aktPreambula,
      aktDeo: aktDeo,
      aktClan: aktClan,
      aktGlava: aktGlava,
      aktOdeljak: aktOdeljak,
      aktPododeljak: aktPododeljak,
      aktStav: aktStav,
      aktTacka: aktTacka,
      aktPodtacka: aktPodtacka,
      aktAlineja: aktAlineja
    };

    /////////////

    function getChildElementsByHtmlID(htmlId, name) {
      return getElementByHtmlId(htmlId).getChildElements(name);
    }

    function getElementByHtmlId(htmlId) {
      return Xonomy.harvestElement(document.getElementById(htmlId));
    }


    function addNewElementWithGeneratedId(htmlId, params) {
      Xonomy.newElementChild(htmlId, params.tag);

      var delovi = getChildElementsByHtmlID(htmlId, params.name);

      delovi = delovi.filter(function (item) {
        return !item.hasAttribute(MetaTagsFactory.metaIdAttr().name);
      });

      delovi.forEach(function (item) {
        Xonomy.newAttribute(item.htmlID, {name: "meta:id", value: item.htmlID})
      });
    }

    function addClan(htmlID, params) {
      addNewElementWithGeneratedId(htmlID, params);

      var clanovi = getChildElementsByHtmlID(htmlID, params.name);

      clanovi.forEach(function (item) {
        var stavovi = item.getChildElements("akt:stav");

        stavovi = stavovi.filter(function (child) {
          return !child.hasAttribute(MetaTagsFactory.metaIdAttr().name)
        });

        stavovi.forEach(function (stav) {
          Xonomy.newAttribute(stav.htmlID, {name: "meta:id", value: stav.htmlID})
        });

      })
    }

    function aktAkt() {
      return {
        name: "akt:akt",
        definition: {
          displayName: "Akt",
          menu: [{
            caption: "Dodaj zaglavlje",
            action: Xonomy.newElementChild,
            actionParameter: aktZaglavlje().tag,
            hideIf: function (elem) {
              return elem.hasChildElement(aktZaglavlje().name);
            }
          }, {
            caption: "Dodaj preambulu",
            action: Xonomy.newElementChild,
            actionParameter: aktPreambula().tag,
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(aktPreambula().name)
            }
          },
            {
              caption: "Dodaj deo",
              action: addNewElementWithGeneratedId,
              actionParameter: aktDeo(),
              hideIf: function (jsElement) {
                return jsElement.hasChildElement(aktClan().name) || jsElement.hasChildElement(aktGlava())
              }
            }, {
              caption: "Dodaj član",
              action: addClan,
              actionParameter: aktClan(),
              hideIf: function (jsElement) {
                return jsElement.hasChildElement(aktDeo().name) || jsElement.hasChildElement(aktGlava().name);
              }
            }, {
              caption: "Dodaj glavu",
              action: addNewElementWithGeneratedId,
              actionParameter: aktGlava(),
              hideIf: function (jsElement) {
                return jsElement.hasChildElement(aktClan().name) || jsElement.hasChildElement(aktDeo().name)
              }
            }]
        }
      }
    }

    function aktZaglavlje() {
      return {
        name: "akt:zaglavlje",
        tag: "<akt:zaglavlje xmlns:akt='" + namespaces.akt + "'></akt:zaglavlje>",
        definition: {
          displayName: "Zaglavlje",
          menu: [{
            caption: "Obriši zaglavlje",
            action: Xonomy.deleteElement
          }, {
            caption: "Dodaj Naziv dokumenta",
            action: Xonomy.newElementChild,
            actionParameter: MetaTagsFactory.metaNaziv().tag,
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(MetaTagsFactory.metaNaziv().name)
            }
          }]
        }
      }
    }

    function aktPreambula() {
      return {
        name: "akt:preambula",
        tag: "<akt:preambula xmlns:akt='" + namespaces.akt + "'></akt:preambula>",
        definition: {
          displayName: "Preambula",
          menu: [
            {
              caption: "Obriši preambulu",
              action: Xonomy.deleteElement
            }
          ],
          hasText: true,
          mustBeAfter: [aktZaglavlje().name],
        }
      }
    }

    function aktDeo(attr) {
      return {
        name: "akt:deo",
        tag: "<akt:deo xmlns:akt='" + namespaces.akt + "'></akt:deo>",
        definition: {
          displayName: "Deo",
          menu: [
            {
              caption: "Obrisi deo",
              action: Xonomy.deleteElement
            },
            {
              caption: "Dodaj glavu",
              action: addNewElementWithGeneratedId,
              actionParameter: aktGlava()
            }
          ],
          mustBeAfter: [aktPreambula().name],
          attributes: attr || {}
        }
      }
    }

    function aktClan(attr) {
      return {
        name: "akt:clan",
        tag: "<akt:clan xmlns:akt='" + namespaces.akt + "'><akt:stav></akt:stav></akt:clan>",
        definition: {
          displayName: "Član",
          menu: [
            {
              caption: "Obriši član",
              action: Xonomy.deleteElement
            }, {
              caption: "Dodaj stav",
              action: addNewElementWithGeneratedId,
              actionParameter: aktStav()
            }

          ],
          mustBeAfter: [aktPreambula().name],
          attributes: attr || {}
        }
      }
    }

    function aktGlava(attr) {
      return {
        name: "akt:glava",
        tag: "<akt:glava xmlns:akt='" + namespaces.akt + "'></akt:glava>",
        definition: {
          displayName: "Glava",
          menu: [{
            caption: "Obriši glavu",
            action: Xonomy.deleteElement
          }, {
            caption: "Dodaj odeljak",
            action: addNewElementWithGeneratedId,
            actionParameter: aktOdeljak(),
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(aktClan().name);
            }
          }, {
            caption: "Dodaj član",
            action: addClan,
            actionParameter: aktClan(),
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(aktOdeljak().name);
            }
          }],
          mustBeAfter: [aktPreambula().name],
          attributes: attr || {}
        }
      }
    }

    function aktOdeljak(attr) {
      return {
        name: "akt:odeljak",
        tag: "<akt:odeljak xmlns:akt='" + namespaces.akt + "'></akt:odeljak>",
        definition: {
          displayName: "Odeljak",
          menu: [
            {
              caption: "Obrisi odeljak",
              action: Xonomy.deleteElement
            }, {
              caption: "Dodaj pododeljak",
              action: addNewElementWithGeneratedId,
              actionParameter: aktPododeljak(),
              hideIf: function (elem) {
                return elem.hasChildElement(aktClan().name)
              }
            }, {
              caption: "Dodaj član",
              action: addClan,
              actionParameter: aktClan(),
              hideIf: function (elem) {
                return elem.hasChildElement(aktPododeljak().name)
              }
            }
          ],
          attributes: attr || {}
        }

      }
    }

    function aktPododeljak(attr) {
      return {
        name: "akt:pododeljak",
        tag: "<akt:pododeljak xmlns:akt='" + namespaces.akt + "'></akt:pododeljak>",
        definition: {
          displayName: "Pododeljak",
          menu: [{
            caption: "Obriši pododeljak",
            action: Xonomy.deleteElement
          }, {
            caption: "Dodaj član",
            action: addClan,
            actionParameter: aktClan()
          }],
          attributes: attr || {}
        }
      }

    }

    function aktStav(attr) {
      return {
        name: "akt:stav",
        tag: "<akt:stav xmlns:akt='" + namespaces.akt + "'></akt:stav>",
        definition: {
          displayName: "Stav",
          menu: [{
            caption: "Dodaj tačku",
            action: addNewElementWithGeneratedId,
            actionParameter: aktTacka()
          }],
          hasText: true,
          attributes: attr || {}
        }
      }
    }

    function aktTacka(attr) {
      return {
        name: "akt:tacka",
        tag: "<akt:tacka xmlns:akt='" + namespaces.akt + "'></akt:tacka>",
        definition: {
          displayName: "Tačka",
          menu: [
            {
              caption: "Obriši tačku",
              action: Xonomy.deleteElement
            },
            {
              caption: "Dodaj podtačku",
              action: addNewElementWithGeneratedId,
              actionParameter: aktPodtacka()
            }
          ],
          hasText: true,
          attributes: attr || {}
        }
      }

    }

    function aktPodtacka(attr) {
      return {
        name: "akt:podtacka",
        tag: "<akt:podtacka xmlns:akt='" + namespaces.akt + "'></akt:podtacka>",
        definition: {
          displayName: "Podtačka",
          menu: [
            {
              caption: "Obriši podtačku",
              action: Xonomy.deleteElement
            }
          ],
          hasText: true,
          attributes: attr || {}

        }

      }
    }

    function aktAlineja(attr) {
      return {
        name: "akt:alineja",
        tag: "<akt:alineja xmlns:akt='" + namespaces.akt + "'></akt:alineja>",
        definition: {
          displayName: "Alineja",
          menu: [
            {
              caption: "Obriši alineju",
              action: Xonomy.deleteElement
            }
          ],
          hasText: true,
          attributes: attr || {}
        },
        parentActions: [
          {
            caption: "Dodaj alineju",
            action: addNewElementWithGeneratedId,
            actionParameter: {
              tag: "<akt:alineja xmlns:akt='" + namespaces.akt + "'></akt:alineja>",
              name: 'akt:alineja'
            }
          }
        ]
      }
    }

  }
})();


