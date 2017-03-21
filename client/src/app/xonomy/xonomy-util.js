(function () {
  'use strict';

  angular
    .module('app')
    .factory('XonomyUtil', XonomyUtil);

  XonomyUtil.$inject = [];
  function XonomyUtil() {
    var service = {
      createWarning: createWarning,
      getChildElementsByHtmlID: getChildElementsByHtmlID,
      getElementByHtmlId: getElementByHtmlId
    };

    return service;

    ////////////////
    function createWarning(jsElement, text) {
      Xonomy.warnings.push({
        htmlID: jsElement.htmlID,
        text: text
      });
    }

    function getChildElementsByHtmlID(htmlId, name) {
      return getElementByHtmlId(htmlId).getChildElements(name);
    }

    function getElementByHtmlId(htmlId) {
      return Xonomy.harvestElement(document.getElementById(htmlId));
    }
  }
})();
