(function () {
  'use strict';

  angular
    .module('app')
    .factory('AmandmanIdsIncrementerService', AmandmanIdsIncrementerService);

  AmandmanIdsIncrementerService.$inject = [];
  function AmandmanIdsIncrementerService() {
    return {
      incrementAndReturn: incrementAndReturn
    };
    ////////////////

    function generateId() {
      // Math.random should be unique because of its seeding algorithm.
      // Convert it to base 36 (numbers + letters), and grab the first 9 characters
      // after the decimal.
      return Math.random().toString(36).substr(2, 9);
    };

    function incrementAndReturn(key) {
      //return key + '-' + (++AmandmanIdCounter[key]);
      return key + '-' + generateId()
    }

  }
})();
