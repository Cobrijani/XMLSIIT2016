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

    function checkIfHasIdWithValue(jsElement, value) {

      var exists = false;

      if (jsElement.type === 'element') {
        jsElement.attributes.forEach(function (attr) {
          exists = attr.name === "meta:id" && attr.value === value;
        });
      } else if (jsElement.type === 'attribute') {
        exists = jsElement.name === "meta:id" && jsElement.value === value;
      }

      return exists;
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
            var workQueue = [];
            var visited = [];
            workQueue.push(jsElement);
            var refExists = false;

            while (workQueue.length > 0 && !refExists) {
              var next = workQueue.shift();

              refExists = checkIfHasIdWithValue(next, jsElement.value);
              visited.push(next.htmlID);

              //add parent to be checked
              if (next.internalParent && visited.indexOf(next.internalParent.htmlID) < 0) {
                workQueue.push(next.internalParent);
              }

              //add children to queue to be checked
              if (next.children) {
                var notVisited = next.children.filter(function (item) {
                  return visited.indexOf(item.htmlID) < 0;
                });
                workQueue = workQueue.concat(notVisited);
              }
            }

            workQueue = null;
            visited = null;
            if (!refExists) {
              XonomyUtil.createWarning(jsElement, "Identifikator ne postoji");
            }
          }
        },
        parentActions: []
      };

    }
  }
})();

