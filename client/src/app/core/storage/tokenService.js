(function () {
  'use strict';

  /*
   Interface that should be exposed to application and used as bridge between application and library
   */
  angular
    .module('block.localStorage')
    .factory('TokenService', TokenService);

  TokenService.$inject = ['localStorageService'];

  function TokenService(localStorageService) {

    var service = {
      saveToken: saveToken,
      clearToken: clearToken,
      clearAll: clearAll,
      getToken: getToken
    };
    return service;

    function saveToken(key, value) {
      return localStorageService.set(key, value);
    }

    function clearToken(key) {
      return localStorageService.remove(key);
    }

    function clearAll() {
      return localStorageService.clearAll();
    }

    function getToken(key) {
      return localStorageService.get(key);
    }
  }
})();
