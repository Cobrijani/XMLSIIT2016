/**
 * Created by SBratic on 3/9/2017.
 */


(function () {
  'use strict';

  angular
    .module('app')
    .factory('MetaTagsFactory', MetaTagsFactory);

  MetaTagsFactory.$inject = ['namespaces'];

  function MetaTagsFactory(namespaces) {

    return {
      metaNaziv: metaNaziv,
      metaIdAttr: metaIdAttr,
      metaNazivAttr: metaNazivAttr
    };

    function metaNaziv(attr, displayName) {
      return {
        name: "meta:naziv",
        tag: "<meta:naziv property='pred:imeDokumenta' datatype='xs:string' xmlns:meta='" + namespaces.meta + "'></meta:naziv>",
        definition: {
          displayName: displayName || "Naziv",
          menu: [
            {
              caption: "Obrisi naziv",
              action: Xonomy.deleteElement
            }
          ],
          hasText: true,
          oneliner: true,
          attributes: attr || {}
        }
      }

    }

    function metaIdAttr() {
      return {
        name: "meta:id",
        attr: {name: "meta:id", value: "id"},
        definition: {},
        parentActions: []
      }
    }

    function metaNazivAttr(actionCaption, displayName) {
      return {
        name: "meta:naziv",
        attr: {name: "meta:naziv", value: "naziv"},
        definition: {
          displayName: displayName || "Naziv",
          asker: Xonomy.askString,
          menu: [
            {
              caption: "Obrisi naziv",
              action: Xonomy.deleteAttribute
            }
          ]
        },
        parentActions: [{
          caption: actionCaption || "Dodaj ime",
          action: Xonomy.newAttribute,
          actionParameter: {name: "meta:naziv", value: "naziv"},
          hideIf: function (elem) {
            return elem.hasAttribute("meta:naziv");
          }
        }]
      }
    }

  }
})();

