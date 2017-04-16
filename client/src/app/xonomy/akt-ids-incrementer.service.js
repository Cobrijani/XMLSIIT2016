(function () {
  'use strict';

  angular
    .module('app')
    .factory('AktIdsIncrementerService', AktIdsIncrementerService);

  AktIdsIncrementerService.$inject = ['AktIdCounter'];
  function AktIdsIncrementerService(AktIdCounter) {
    return {
      incrementAndReturn: incrementAndReturn,
      resetAllCounters: resetAllCounters
    };
    ////////////////

    function incrementAndReturn(key) {
      return key + '-' + (++AktIdCounter[key]);
    }

    function resetAllCounters() {
      for (var property in AktIdCounter) {
        if (AktIdCounter.hasOwnProperty(property)) {
          AktIdCounter[property] = 0;
        }
      }
    }
  }
})();
