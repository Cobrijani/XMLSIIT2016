/**
 * Definition for rdf tags and attributes
 * Created by SBratic on 3/10/2017.
 */


function rdfPropertyAttr(value) {
  return {
    name: "property",
    attr: {name: "property", value: value || "default"},
    definition: {
      isInvisible: true
    },
    parentActions: []
  }
}

function rdfDataTypeAttr(value) {
  return {
    name: "datatype",
    attr: {name: "datatype", value: value || "xs:string"},
    definition: {
      isInvisible: true
    },
    parentActions: []
  }
}
