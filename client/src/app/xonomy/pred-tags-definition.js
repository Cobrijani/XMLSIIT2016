/**
 * Definition for rdf tags and attributes
 * Created by SBratic on 3/10/2017.
 */



(function () {
  'use strict';

  angular
    .module('app')
    .factory('RdfaTagsFactory', RdfaTagsFactory);

  RdfaTagsFactory.$inject = [];

  function RdfaTagsFactory() {

    return {
      rdfPropertyAttr: rdfPropertyAttr,
      rdfDataTypeAttr: rdfDataTypeAttr
    };

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


  }
})();


