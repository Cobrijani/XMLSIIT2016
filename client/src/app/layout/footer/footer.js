/**
 * Created by Micko on 28-Dec-16.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .directive('myFooter', myFooter);

  function myFooter() {
    var directive = {
      templateUrl: "app/layout/footer/footer.html",
      restrict: 'E'
    };
    return directive;
  }

})();

