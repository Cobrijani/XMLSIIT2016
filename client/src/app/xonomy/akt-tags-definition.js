/**
 * This file contains definitions for Xonomy for each tag for akt.xsd
 * Created by SBratic on 3/9/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .factory('AktTagsFactory', AktTagsFactory);

  AktTagsFactory.$inject = ['MetaTagsFactory', 'namespaces', 'XonomyUtil', 'AktIdsIncrementerService'];

  function AktTagsFactory(MetaTagsFactory, namespaces, XonomyUtil, AktIdsIncrementerService) {

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
      aktAlineja: aktAlineja,
      aktReferenca: aktReferenca
    };

    /////////////

    function idCheck(jsElement, message) {
      if (!jsElement.hasAttribute("meta:id")) {
        XonomyUtil.createWarning(jsElement, message || "Identifikator mora postojati");
      }
    }


    function addNewElementWithGeneratedId(htmlId, params) {
      Xonomy.newElementChild(htmlId, params.tag);

      var delovi = XonomyUtil.getChildElementsByHtmlID(htmlId, params.name);

      delovi = delovi.filter(function (item) {
        return !item.hasAttribute(MetaTagsFactory.metaIdAttr().name);
      });

      delovi.forEach(function (item) {
        Xonomy.newAttribute(item.htmlID, {
          name: "meta:id", value: AktIdsIncrementerService.incrementAndReturn(item.name)
        });
      });
    }

    function addClan(htmlID, params) {
      addNewElementWithGeneratedId(htmlID, params);

      var clanovi = XonomyUtil.getChildElementsByHtmlID(htmlID, params.name);

      clanovi.forEach(function (item) {
        var stavovi = item.getChildElements("akt:stav");

        stavovi = stavovi.filter(function (child) {
          return !child.hasAttribute(MetaTagsFactory.metaIdAttr().name);
        });

        stavovi.forEach(function (stav) {
          Xonomy.newAttribute(stav.htmlID, {
            name: "meta:id", value: AktIdsIncrementerService.incrementAndReturn(stav.name)
          });
        });

      });
    }

    function aktAkt() {
      return {
        name: "akt:akt",
        definition: {
          displayName: "Akt",
          hasText: false,
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
              return jsElement.hasChildElement(aktPreambula().name);
            }
          },
          {
            caption: "Dodaj deo",
            action: addNewElementWithGeneratedId,
            actionParameter: aktDeo(),
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(aktClan().name) || jsElement.hasChildElement(aktGlava());
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
              return jsElement.hasChildElement(aktClan().name) || jsElement.hasChildElement(aktDeo().name);
            }
          }],
          validate: function (jsElement) {
            if (!jsElement.hasChildElement('akt:zaglavlje')) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Akt mora imati zaglavlje"
              });
            }

            if (!jsElement.hasChildElement("akt:clan") && !jsElement.hasChildElement("akt:glava") && !jsElement.hasChildElement("akt:deo")) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Akt se mora sastojati ili od članova ili glava ili delova'
              });
            }

            //mora imati 2-20 clana ili 1-* deo ili 1-*glava
            if (jsElement.hasChildElement("akt:clan")) {
              if (jsElement.getChildElements("akt:clan").length < 2) {
                Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                  text: 'Akt mora imati najmanje 2 člana'
                });
              }
              if (jsElement.getChildElements("akt:clan").length > 20) {
                Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                  text: 'Akt može imati najviše 20 članova'
                });
              }
            }
          }
        }
      };
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
              return jsElement.hasChildElement(MetaTagsFactory.metaNaziv().name);
            }
          }],
          validate: function (jsElement) {
            if (!jsElement.hasChildElement("meta:naziv")) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'U zaglavlju mora da postoji naziv akta'
              });
            }
          }
        }
      };
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
          mustBeAfter: [aktZaglavlje().name]
        }
      };
    }

    function aktDeo(attr) {
      return {
        name: "akt:deo",
        tag: "<akt:deo xmlns:akt='" + namespaces.akt + "'></akt:deo>",
        definition: {
          displayName: "Deo",
          menu: [
            {
              caption: "Obriši deo",
              action: Xonomy.deleteElement
            },
            {
              caption: "Dodaj glavu",
              action: addNewElementWithGeneratedId,
              actionParameter: aktGlava()
            }
          ],
          mustBeAfter: [aktPreambula().name],
          attributes: attr || {},
          validate: function (jsElement) {
            idCheck(jsElement, "Deo mora imat identifikator");
            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Deo mora imati svoj naziv");
            }
            if (jsElement.getChildElements("akt:glava").length < 2) {
              XonomyUtil.createWarning(jsElement, "Deo morati imati najmanje dve glave");
            }
          }
        }
      };
    }

    function aktClan(attr) {
      return {
        name: "akt:clan",
        tag: "<akt:clan xmlns:akt='" + namespaces.akt + "' xmlns:meta='" + namespaces.meta + "' meta:naziv='naziv'><akt:stav></akt:stav></akt:clan>",
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
          attributes: attr || {},
          validate: function (jsElement) {
            idCheck(jsElement, "Član mora imati identifikator");
            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Član mora imati naziv");
            }
            if (!jsElement.hasChildElement("akt:stav")) {
              XonomyUtil.createWarning(jsElement, "Član mora imati najmanje jedan stav");
            }
          }
        }
      };
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
          attributes: attr || {},
          validate: function (jsElement) {

            idCheck(jsElement, "Glava mora imati identifikator");

            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Glava mora imati naziv");
            }
            if (!jsElement.hasChildElement("akt:odeljak") && !jsElement.hasChildElement("akt:clan")) {
              XonomyUtil.createWarning(jsElement, "Glava se mora sastojati ili od članova ili odeljaka");
            }
          }
        }
      };
    }

    function aktOdeljak(attr) {
      return {
        name: "akt:odeljak",
        tag: "<akt:odeljak xmlns:akt='" + namespaces.akt + "'></akt:odeljak>",
        definition: {
          displayName: "Odeljak",
          menu: [
            {
              caption: "Obriši odeljak",
              action: Xonomy.deleteElement
            }, {
              caption: "Dodaj pododeljak",
              action: addNewElementWithGeneratedId,
              actionParameter: aktPododeljak(),
              hideIf: function (elem) {
                return elem.hasChildElement(aktClan().name);
              }
            }, {
              caption: "Dodaj član",
              action: addClan,
              actionParameter: aktClan(),
              hideIf: function (elem) {
                return elem.hasChildElement(aktPododeljak().name);
              }
            }
          ],
          attributes: attr || {},
          validate: function (jsElement) {

            idCheck(jsElement, "Odeljak mora imati identifikator");
            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Odeljak mora imati naziv");
            }

            if (!jsElement.hasChildElement("akt:pododeljak") && !jsElement.hasChildElement("akt:clan")) {
              XonomyUtil.createWarning(jsElement, "Odeljak se mora sastojati ili od podeljaka ili od članova");
            }
          }
        }

      };
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
          attributes: attr || {},
          validate: function (jsElement) {

            idCheck(jsElement, "Pododeljak mora imati identifikator");

            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Pododeljak mora imati naziv");
            }

            if (!jsElement.hasChildElement("akt:clan")) {
              XonomyUtil.createWarning(jsElement, "Pododeljak mora imat najmanje jedan član");
            }
          }
        }
      };

    }

    function aktStav(attr) {
      return {
        name: "akt:stav",
        tag: "<akt:stav xmlns:akt='" + namespaces.akt + "'></akt:stav>",
        definition: {
          displayName: "Stav",
          menu: [{
            caption: "Obriši stav",
            action: Xonomy.deleteElement
          }, {
            caption: "Dodaj tačku",
            action: addNewElementWithGeneratedId,
            actionParameter: aktTacka()
          }],
          inlineMenu: [],
          hasText: true,
          attributes: attr || {},
          validation: function (jsElement) {
            idCheck(jsElement, "Stav mora imati identifikator");
          }
        }
      };
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
          attributes: attr || {},
          validate: function (jsElement) {

            idCheck(jsElement, "Tačka mora imati identifikator");

            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Tačka mora imati naziv");
            }

            if (jsElement.getChildElements("akt:podtacka").length < 2) {
              XonomyUtil.createWarning(jsElement, "Tačka mora imati bar dve podtačke");
            }
          }
        }
      };

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
          attributes: attr || {},
          validate: function (jsElement) {

            idCheck(jsElement, "Podtačka mora imati identifikator");

            if (!jsElement.hasAttribute("meta:naziv")) {
              XonomyUtil.createWarning(jsElement, "Podtačka mora imati naziv");
            }

            if (jsElement.getChildElements("akt:alineja").length < 2) {
              XonomyUtil.createWarning(jsElement, "Podtačka mora imati bar dve alieneje");
            }
          }

        }

      };
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
      };
    }

    function aktReferenca() {
      return {
        name: "akt:referenca",
        tag: "<akt:referenca xmlns:akt='" + namespaces.akt + "'></akt:referenca>",
        definition: {
          displayName: "Ref",
          menu: [
            {
              caption: "Obriši referencu",
              action: Xonomy.unwrap,

            }
          ]
        },
        parentActions: [],
        parentInlineActions: [{
          caption: "Napravi referencu",
          action: Xonomy.wrap,
          actionParameter: {
            template: "<akt:referenca xmlns:akt='" + namespaces.akt + "' xmlns:meta='" + namespaces.meta + "' meta:idRef='Unesi naziv identifikator koji želiš da referenciraš'>$</akt:referenca>",
            placeholder: "$"
          }
        }]
      };
    }

  }
})();


