(function () {
  'use strict';

  angular
    .module('app')
    .directive('xonomy', xonomyDirective);

  function xonomyDirective() {
    return {
      scope: {
        mode: '@',
        startContent: '='
      },
      template: '<div></div>',
      controller: controller,
      controllerAs: 'vm',
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
        " xmlns:meta='http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata' ><akt:zaglavlje><meta:naziv property='pred:imeDokumenta' datatype='xs:string'></meta:naziv></akt:zaglavlje></akt:akt>";

      Xonomy.render(scope.startContent || xml, element[0], aktSpec)
    }

    function controller($scope) {
      var vm = this;
      Xonomy.setMode($scope.mode || 'laic');
    }
  }
})();
