/**
 * This file contains definitions for Xonomy for each tag for akt.xsd
 * Created by SBratic on 3/9/2017.
 */

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
          action: Xonomy.newElementChild,
          actionParameter: aktDeo().tag,
          hideIf: function (jsElement) {
            return jsElement.hasChildElement(aktClan().name) || jsElement.hasChildElement(aktGlava())
          }
        }, {
          caption: "Dodaj član",
          action: Xonomy.newElementChild,
          actionParameter: aktClan().tag,
          hideIf: function (jsElement) {
            console.log(jsElement);
            return jsElement.hasChildElement(aktDeo().name) || jsElement.hasChildElement(aktGlava().name);
          }
        }, {
          caption: "Dodaj glavu",
          action: Xonomy.newElementChild,
          actionParameter: aktGlava().tag,
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
    tag: "<akt:zaglavlje xmlns:akt='" + aktNamespace + "'></akt:zaglavlje>",
    definition: {
      displayName: "Zaglavlje",
      menu: [{
        caption: "Obriši zaglavlje",
        action: Xonomy.deleteElement
      }, {
        caption: "Dodaj Naziv dokumenta",
        action: Xonomy.newElementChild,
        actionParameter: metaNaziv().tag,
        hideIf: function (jsElement) {
          return jsElement.hasChildElement(metaNaziv().name)
        }
      }]
    }
  }
}

function aktPreambula() {
  return {
    name: "akt:preambula",
    tag: "<akt:preambula xmlns:akt='" + aktNamespace + "'></akt:preambula>",
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
  }
}


function aktDeo(attr) {
  return {
    name: "akt:deo",
    tag: "<akt:deo xmlns:akt='" + aktNamespace + "'></akt:deo>",
    definition: {
      displayName: "Deo",
      menu: [
        {
          caption: "Obrisi deo",
          action: Xonomy.deleteElement
        },
        {
          caption: "Dodaj glavu",
          action: Xonomy.newElementChild,
          actionParameter: aktGlava().tag
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
    tag: "<akt:clan xmlns:akt='" + aktNamespace + "'><akt:stav></akt:stav></akt:clan>",
    definition: {
      displayName: "Član",
      menu: [
        {
          caption: "Obriši član",
          action: Xonomy.deleteElement
        }, {
          caption: "Dodaj stav",
          action: Xonomy.newElementChild,
          actionParameter: aktStav().tag
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
    tag: "<akt:glava xmlns:akt='" + aktNamespace + "'></akt:glava>",
    definition: {
      displayName: "Glava",
      menu: [{
        caption: "Obriši glavu",
        action: Xonomy.deleteElement
      }, {
        caption: "Dodaj odeljak",
        action: Xonomy.newElementChild,
        actionParameter: aktOdeljak().tag,
        hideIf: function (jsElement) {
          return jsElement.hasChildElement(aktClan().name);
        }
      }, {
        caption: "Dodaj član",
        action: Xonomy.newElementChild,
        actionParameter: aktClan().tag,
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
    tag: "<akt:odeljak xmlns:akt='" + aktNamespace + "'></akt:odeljak>",
    definition: {
      displayName: "Odeljak",
      menu: [
        {
          caption: "Obrisi odeljak",
          action: Xonomy.deleteElement
        }, {
          caption: "Dodaj pododeljak",
          action: Xonomy.newElementChild,
          actionParameter: aktPododeljak().tag,
          hideIf: function (elem) {
            return elem.hasChildElement(aktClan().name)
          }
        }, {
          caption: "Dodaj član",
          action: Xonomy.newElementChild,
          actionParameter: aktClan().tag,
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
    tag: "<akt:pododeljak xmlns:akt='" + aktNamespace + "'></akt:pododeljak>",
    definition: {
      displayName: "Pododeljak",
      menu: [{
        caption: "Obriši pododeljak",
        action: Xonomy.deleteElement
      }],
      attributes: attr || {}
    }
  }

}

function aktStav(attr) {
  return {
    name: "akt:stav",
    tag: "<akt:stav xmlns:akt='" + aktNamespace + "'></akt:stav>",
    definition: {
      displayName: "Stav",
      menu: [{
        caption: "Dodaj tačku",
        action: Xonomy.newElementChild,
        actionParameter: aktTacka().tag
      }],
      hasText: true,
      attributes: attr || {}
    }
  }
}


function aktTacka(attr) {
  return {
    name: "akt:tacka",
    tag: "<akt:tacka xmlns:akt='" + aktNamespace + "'></akt:tacka>",
    definition: {
      displayName: "Tačka",
      menu: [
        {
          caption: "Obriši tačku",
          action: Xonomy.deleteElement
        },
        {
          caption: "Dodaj podtačku",
          action: Xonomy.newElementChild,
          actionParameter: aktPodtacka().tag
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
    tag: "<akt:podtacka xmlns:akt='" + aktNamespace + "'></akt:podtacka>",
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
    tag: "<akt:alineja xmlns:akt='" + aktNamespace + "'></akt:alineja>",
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
        action: Xonomy.newElementChild,
        actionParameter: "<akt:alineja xmlns:akt='" + aktNamespace + "'></akt:alineja>"
      }
    ]
  }
}

