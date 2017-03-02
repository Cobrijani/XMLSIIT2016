(function () {
  'use strict';

  angular
    .module('block.common')
    .directive('compareTo', compareToDirective);

  function compareToDirective() {
    return {
      require: 'ngModel',
      scope: {
        otherModelValue: '=compareTo'
      },
      link: link
    };

    function link(scope, element, attributes, ngModel) {
      ngModel.$validators.compareTo = function (modelValue) {
        return modelValue === scope.otherModelValue;
      };

      scope.$watch('otherModelValue', function () {
        ngModel.$validate();
      });
    }
  }
})();
