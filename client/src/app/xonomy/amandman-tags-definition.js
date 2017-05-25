/**
 * This file contains definitions for Xonomy for each tag for amandman.xsd
 * Created by Arsa on 5/20/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .factory('AmandmanTagsFactory', AmandmanTagsFactory);

  AmandmanTagsFactory.$inject = ['MetaTagsFactory', 'namespaces', 'XonomyUtil', 'AmandmanIdsIncrementerService', 'AktIdsIncrementerService'];

  function AmandmanTagsFactory(MetaTagsFactory, namespaces, XonomyUtil, AmandmanIdsIncrementerService, AktIdsIncrementerService) {

    return {
      amandmanAmandman: amandmanAmandman,
      amandmanZaglavljeAmandman: amandmanZaglavljeAmandman,
      amandmanIzmene: amandmanIzmene,
      amandmanIzmena: amandmanIzmena,
      amandmanPredmetIzmene: amandmanPredmetIzmene,
      amandmanResenja: amandmanResenja,
      amandmanResenje: amandmanResenje,
      amandmanObrazlozenje: amandmanObrazlozenje,
      amandmanRazlogPodnosenja: amandmanRazlogPodnosenja,
      amandmanObjasnjenjeResenja: amandmanObjasnjenjeResenja,
      amandmanCilj: amandmanCilj,
      amandmanProcenaUticaja: amandmanProcenaUticaja,
      amandmanDocumentAmRef: amandmanDocumentAmRef,
      aktClan: aktClan,
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
          name: "meta:id", value: AmandmanIdsIncrementerService.incrementAndReturn(item.name)
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

    function amandmanAmandman() {
      return {
        name: "am:amandman",
        definition: {
          displayName: "Amandman",
          hasText: false,
          menu: [{
            caption: "Dodaj zaglavlje",
            action: Xonomy.newElementChild,
            actionParameter: amandmanZaglavljeAmandman().tag,
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(amandmanZaglavljeAmandman().name);
            }
          }, {
            caption: "Dodaj obrazloženje",
            action: Xonomy.newElementChild,
            actionParameter: amandmanObrazlozenje().tag,
            hideIf: function (jsElement) {
              return jsElement.hasChildElement(amandmanObrazlozenje().name);
            }
          }],
          validate: function (jsElement) {
            if (!jsElement.hasChildElement('am:zaglavlje_amandman')) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Amandman mora imati zaglavlje"
              });
            }

            if (!jsElement.hasChildElement("am:izmene")) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Amandman mora imati bar jednu izmenu'
              });
            }

          }
        }
      };
    }

    function amandmanZaglavljeAmandman() {
      return {
        name: "am:zaglavlje_amandman",
        tag: "<am:zaglavlje_amandman xmlns:am='" + namespaces.amandman + "'><am:akt_ref/></am:zaglavlje_amandman>",
        definition: {
          displayName: "Zaglavlje",
          menu: [{
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
                text: 'U zaglavlju mora da postoji naziv amandmana'
              });
            }
          }
        }
      };
    }

    function amandmanIzmene(aktReferences) {
      return {
        name: "am:izmene",
        tag: "<am:izmene xmlns:am='" + namespaces.amandman + "'></am:izmene>",
        definition: {
          displayName: "Izmene",
          hasText: false,
          menu: [{
            caption: "Dodaj izmenu",
            action: Xonomy.newElementChild,
            actionParameter: amandmanIzmena(aktReferences).tag
          }],
          validate: function (jsElement) {
            if (!jsElement.hasChildElement(amandmanIzmena(aktReferences).name)) {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Amandman mora imati bar jednu izmenu'
              });
            }

          }
        }
      };
    }



    function amandmanIzmena(aktReferences) {
      return {
        name: "am:izmena",
        tag: "<am:izmena xmlns:am='" + namespaces.amandman + "'><am:predmet_izmene tip_izmene='izmena'></am:predmet_izmene><am:resenja></am:resenja></am:izmena>",
        definition: {
          displayName: "Izmena",
          menu: [
            {
              caption: "Obriši izmenu",
              action: Xonomy.deleteElement
            }, {
              caption: "Dodaj predmet izmene",
              action: Xonomy.newElementChild,
              actionParameter: amandmanPredmetIzmene(aktReferences).tag,
              hideIf: function (jsElement) {
                return jsElement.hasChildElement(amandmanPredmetIzmene(aktReferences).name);
              }
            }

          ],
          validate: function (jsElement) {

          }
        }
      };
    }


    function amandmanPredmetIzmene(aktReferences) {
      return {
        name: "am:predmet_izmene",
        tag: "<am:predmet_izmene xmlns:am='" + namespaces.amandman + "' tip_izmene='izmena'></am:predmet_izmene>",
        definition: {
          displayName: "Predmet izmene",
          hasText: true,
          attributes: {
              "tip_izmene": {
                asker: Xonomy.askPicklist,
                askerParameter: ["izmena", "brisanje", "dopuna"]
              },
              "ref_clanovi": {
                asker: Xonomy.askPicklist,
                askerParameter: aktReferences.clanovi,
                menu: [{
                  caption: "Obriši atribut ref_clanovi",
                  action: Xonomy.deleteAttribute,
                }]
              },
              "ref_stavovi": {
                asker: Xonomy.askPicklist,
                askerParameter: aktReferences.stavovi,
                menu: [{
                  caption: "Obriši atribut ref_stavovi",
                  action: Xonomy.deleteAttribute,
                }]
              },
              "ref_tacke": {
                asker: Xonomy.askPicklist,
                askerParameter: aktReferences.tacke,
                menu: [{
                  caption: "Obriši atribut ref_tacke",
                  action: Xonomy.deleteAttribute,
                }]
              },
              "ref_podtacke": {
                asker: Xonomy.askPicklist,
                askerParameter: aktReferences.podtacke,
                menu: [{
                  caption: "Obriši atribut ref_podtacke",
                  action: Xonomy.deleteAttribute,
                }]
              },
              "ref_alineje": {
                asker: Xonomy.askPicklist,
                askerParameter: aktReferences.alineje,
                menu: [{
                  caption: "Obriši atribut ref_alineje",
                  action: Xonomy.deleteAttribute,
                }]
              }
            },

          menu: [
            {
              caption: "Obriši predmet izmene",
              action: Xonomy.deleteElement
            },
            {
              caption: "Dodaj referencu za članove",
              action:  Xonomy.newAttribute,
              actionParameter: {name: "ref_clanovi", value: ""},
              hideIf: function (jsElement) {
                return jsElement.hasAttribute("ref_clanovi") || jsElement.hasAttribute("ref_stavovi") || jsElement.hasAttribute("ref_tacke") ||
                  jsElement.hasAttribute("ref_podtacke") || jsElement.hasAttribute("ref_alineje");
              }
            },
            {
              caption: "Dodaj referencu za stavove",
              action:  Xonomy.newAttribute,
              actionParameter: {name: "ref_stavovi", value: ""},
              hideIf: function (jsElement) {
                return jsElement.hasAttribute("ref_clanovi") || jsElement.hasAttribute("ref_stavovi") || jsElement.hasAttribute("ref_tacke") ||
                  jsElement.hasAttribute("ref_podtacke") || jsElement.hasAttribute("ref_alineje");
              }
            },
            {
              caption: "Dodaj referencu za tačke",
              action:  Xonomy.newAttribute,
              actionParameter: {name: "ref_tacke", value: ""},
              hideIf: function (jsElement) {
                return jsElement.hasAttribute("ref_clanovi") || jsElement.hasAttribute("ref_stavovi") || jsElement.hasAttribute("ref_tacke") ||
                  jsElement.hasAttribute("ref_podtacke") || jsElement.hasAttribute("ref_alineje");
              }
            },
            {
              caption: "Dodaj referencu za podtačke",
              action:  Xonomy.newAttribute,
              actionParameter: {name: "ref_podtacke", value: ""},
              hideIf: function (jsElement) {
                return jsElement.hasAttribute("ref_clanovi") || jsElement.hasAttribute("ref_stavovi") || jsElement.hasAttribute("ref_tacke") ||
                  jsElement.hasAttribute("ref_podtacke") || jsElement.hasAttribute("ref_alineje");
              }
            },
            {
              caption: "Dodaj referencu za alineje",
              action:  Xonomy.newAttribute,
              actionParameter: {name: "ref_alineje", value: ""},
              hideIf: function (jsElement) {
                return jsElement.hasAttribute("ref_clanovi") || jsElement.hasAttribute("ref_stavovi") || jsElement.hasAttribute("ref_tacke") ||
                  jsElement.hasAttribute("ref_podtacke") || jsElement.hasAttribute("ref_alineje");
              }
            }

          ],
          mustBeBefore: [amandmanResenja().name],
          validate: function (jsElement) {

          }
        }
      };
    }




    function amandmanResenja() {
      return {
        name: "am:resenja",
        tag: "<am:resenja xmlns:am='" + namespaces.amandman + "'></am:resenja>",
        definition: {
          displayName: "Rešenja",
          hasText: false,
          menu: [{
            caption: "Dodaj rešenje",
            action: Xonomy.newElementChild,
            actionParameter: amandmanResenje().tag
          }],
          validate: function (jsElement) {


          }
        }
      };
    }

    function amandmanResenje(){
      return {
        name: "am:resenje",
        tag: "<am:resenje xmlns:am='" + namespaces.amandman + "'></am:resenje>",
        definition: {
          displayName: "Rešenje",
          hasText: false,
          menu: [
            {
              caption: "Obriši rešenje",
              action: Xonomy.deleteElement
            }, {
              caption: "Dodaj član",
              action: addClan,
              actionParameter: aktClan(),
              showIf: function (jsElement) {
                return jsElement.getParent().hasChildElement(aktClan()) || jsElement.getParent().getParent().getChildElements().size() == 0;
              }
            }, {
              caption: "Dodaj stav",
              action: addNewElementWithGeneratedId,
              actionParameter: aktStav(),
              hideIf: function (jsElement) {

              }
            }, {
              caption: "Dodaj tačku",
              action: addNewElementWithGeneratedId,
              actionParameter: aktTacka()
            }, {
              caption: "Dodaj podtačku",
              action: addNewElementWithGeneratedId,
              actionParameter: aktPodtacka()
            }
          ],
          validate: function (jsElement) {


          }
        }
      };
    }

    function amandmanObrazlozenje() {
      return {
        name: "am:obrazlozenje",
        tag: "<am:obrazlozenje xmlns:am='" + namespaces.amandman + "'><am:razlog_podnosenja></am:razlog_podnosenja><am:objasnjenje_resenja></am:objasnjenje_resenja><am:cilj></am:cilj><am:procena_uticaja></am:procena_uticaja></am:obrazlozenje>",
        definition: {
          displayName: "Obrazloženje",
          hasText: false,
          menu: [
            {
              caption: "Obriši obrazloženje",
              action: Xonomy.deleteElement
            }

          ],
          validate: function (jsElement) {

          }
        }
      };
    }

    function amandmanRazlogPodnosenja() {
      return {
        name: "am:razlog_podnosenja",
        tag: "<am:razlog_podnosenja></am:razlog_podnosenja>",
        definition: {
          displayName: "Razlog podnošenja",
          hasText: true,
          menu: [],
          validate: function (jsElement) {

          }
        }
      };
    }

    function amandmanObjasnjenjeResenja() {
      return {
        name: "am:objasnjenje_resenja",
        tag: "<am:objasnjenje_resenja></am:objasnjenje_resenja>",
        definition: {
          displayName: "Objašnjenje rešenja",
          hasText: true,
          mustBeBefore: "am:document_am_ref",
          menu: [],
          validate: function (jsElement) {

          }
        }
      };
    }

    function amandmanCilj() {
      return {
        name: "am:cilj",
        tag: "<am:cilj></am:cilj>",
        definition: {
          displayName: "Cilj",
          hasText: true,
          menu: [],
          validate: function (jsElement) {

          }
        }
      };
    }

    function amandmanDocumentAmRef() {
      return {
        name: "am:document_am_ref",
        tag: "<am:cilj></am:cilj>",
        definition: {
          displayName: "Document Amandman Reference",
          hasText: true,
          menu: [],
          validate: function (jsElement) {

          }
        }
      };
    }

    function amandmanProcenaUticaja() {
      return {
        name: "am:procena_uticaja",
        tag: "<am:procena_uticaja></am:procena_uticaja>",
        definition: {
          displayName: "Procena uticaja",
          hasText: true,
          menu: [],
          validate: function (jsElement) {

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
          //mustBeAfter: [aktPreambula().name],
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


