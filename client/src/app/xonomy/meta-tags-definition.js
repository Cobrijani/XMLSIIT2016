/**
 * Created by SBratic on 3/9/2017.
 */

function metaNaziv(attr, displayName) {
  return {
    name: "meta:naziv",
    tag: "<meta:naziv property='pred:imeDokumenta' datatype='xs:string' xmlns:meta='" + metaNamespace + "'></meta:naziv>",
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
