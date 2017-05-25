(function () {
  'use strict';

  angular
    .module('app')
    .factory('AmandmanIdsIncrementerService', AmandmanIdsIncrementerService);

  AmandmanIdsIncrementerService.$inject = ['AmandmanIdCounter'];
  function AmandmanIdsIncrementerService(AmandmanIdCounter) {
    return {
      incrementAndReturn: incrementAndReturn,
      resetAllCounters: resetAllCounters
    };
    ////////////////

    function incrementAndReturn(key) {
      return key + '-' + (++AmandmanIdCounter[key]);
    }

    function resetAllCounters() {
      for (var property in AmandmanIdCounter) {
        if (AmandmanIdCounter.hasOwnProperty(property)) {
          AmandmanIdCounter[property] = 0;
        }
      }
    }
  }
})();
