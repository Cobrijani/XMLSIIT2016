(function () {
  'use strict';

  angular
    .module('app')
    .directive('xonomy', xonomyDirective);

  function xonomyDirective() {
    return {
      scope: {
        mode: '=',
        startContent: '=',
        spec: '='
      },
      template: '<div id="xonomy-place" ng-show="xonomyMode"></div>',
      controller: controller,
      compile: compileFunction,
      link: linkFunction,
      restrict: 'E',
    };


    function compileFunction(element, attrs) {
      return linkFunction;
    }

    function linkFunction(scope, element, attrs, controller) {
      var xml = "<akt:akt xmlns:akt='http://parlament.gov.rs/rs.ac.uns.ftn.model.akt'" +
        " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'" +
        " xmlns:meta='http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata'" +
        " xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'" +
        " xmlns='http://www.w3.org/ns/rdfa#' >" +
        "<akt:zaglavlje><meta:naziv property='pred:imeDokumenta' datatype='xs:string'></meta:naziv>" +
        "</akt:zaglavlje></akt:akt>";

      Xonomy.render(scope.startContent || xml, element[0], scope.spec);
      Xonomy.setMode(scope.mode || 'laic');
      scope.$watch('mode', function (newValue, oldValue) {
        if (newValue !== oldValue) {
          Xonomy.setMode(newValue);
        }
      });

      scope.$watch('startContent', function (newValue, oldValue) {
        Xonomy.render(newValue || xml, element[0], scope.spec)
      })
    }

    function controller($scope) {


    }
  }
})();
