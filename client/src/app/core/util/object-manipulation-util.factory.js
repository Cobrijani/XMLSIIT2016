(function () {
  'use strict';

  angular
    .module('block.util')
    .factory('ObjectManipulationUtilFactory', ObjectManipulationUtilFactory);

  ObjectManipulationUtilFactory.$inject = ['_'];

  /* @ngInject */
  function ObjectManipulationUtilFactory(_) {
    return {
      convertPropertiesToOptionArray: convertPropertiesToOptionArray
    };

    /**
     * @ngdoc convert constant of properties into array
     * @name
     * @description
     *
     *
     */
    function convertPropertiesToOptionArray(object) {
      var array = [];
      _.forIn(object, function (value) {
        array.push({
          id: value.id,
          value: value.value
        });
      });
      return array;
    }
  }
})();
