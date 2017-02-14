/**
 * Created by SBratic on 1/24/2017.
 */
(function () {
  'use strict';

  angular
    .module('block.common')
    .directive('errSrc', errSrc);

  function errSrc() {
    return {
      link: function (scope, element, attrs) {

        scope.$watch(function () {
          return attrs.ngSrc;
        }, function (value) {
          if (!value) {
            element.attr('src', attrs.errSrc);
          }
        });

        element.bind('error', function () {
          element.attr('src', attrs.errSrc);
        });
      }
    };
  }
})();
