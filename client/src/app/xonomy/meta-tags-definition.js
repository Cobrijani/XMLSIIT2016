/**
 * Created by SBratic on 3/9/2017.
 */


(function () {
  'use strict';

  angular
    .module('app')
    .factory('MetaTagsFactory', MetaTagsFactory);

  MetaTagsFactory.$inject = ['namespaces', 'XonomyUtil'];

  function MetaTagsFactory(namespaces, XonomyUtil) {

    return {
      metaNaziv: metaNaziv,
      metaIdAttr: metaIdAttr,
      metaNazivAttr: metaNazivAttr,
      metaIdRefAttr: metaIdRefAttr
    };

    function metaNaziv(attr, displayName) {
      return {
        name: "meta:naziv",
        tag: "<meta:naziv property='pred:imeDokumenta' datatype='xs:string' xmlns:meta='" + namespaces.meta + "'></meta:naziv>",
        definition: {
          displayName: displayName || "Naziv",
          menu: [
            {
              caption: "Obriši naziv",
              action: Xonomy.deleteElement
            }
          ],
          hasText: true,
          oneliner: true,
          attributes: attr || {},
          validate: function (jsElement) {
            if (jsElement.getText().replace(/\s+/, "") === '') {
              XonomyUtil.createWarning(jsElement, "Naziv mora imati nepraznu vrednost");
            }

          }
        }
      };

    }

    function metaIdAttr() {
      return {
        name: "meta:id",
        attr: {
          name: "meta:id", value: "id"
        },
        definition: {
          displayName: "Identifikator",
          validate: function (jsElement) {
            if (jsElement.value.replace(/\s+/, "") === '') {
              XonomyUtil.createWarning(jsElement, "Identifikator mora imati nepraznu vrednost");
            }
          }
        },
        parentActions: []
      };
    }

    function metaNazivAttr(actionCaption, displayName) {
      return {
        name: "meta:naziv",
        attr: {
          name: "meta:naziv", value: "naziv"
        },
        definition: {
          displayName: displayName || "Naziv",
          asker: Xonomy.askString,
          menu: [
            {
              caption: "Obriši naziv",
              action: Xonomy.deleteAttribute
            }
          ],
          validate: function (jsElement) {
            if (jsElement.value.replace(/\s+/, "") === '') {
              XonomyUtil.createWarning(jsElement, "Naziv mora imati nepraznu vrednost");
            }
          }
        },
        parentActions: [{
          caption: actionCaption || "Dodaj ime",
          action: Xonomy.newAttribute,
          actionParameter: {
            name: "meta:naziv", value: "naziv"
          },
          hideIf: function (elem) {
            return elem.hasAttribute("meta:naziv");
          }
        }]
      };
    }

    function metaIdRefAttr(actionCaption, displayName) {
      return {
        name: "meta:idRef",
        attr: {
          name: "meta:idRef", value: "Unesi identifikator za element koji zelis da referencira"
        },
        definition: {
          displayName: displayName || "Identifikator elementa",
          asker: Xonomy.askString,
          validate: function (jsElement) {
            try {
              XonomyUtil.getElementByHtmlId(jsElement.value);
            } catch (error) {
              XonomyUtil.createWarning(jsElement, "Referenca mora imati postojeći identifikator");
            }
          }
        },
        parentActions: []
      };

    }
  }
})();

