(function () {
  'use strict';

  angular
    .module('block.exception')
    .factory('exception', ExceptionCatcherFactory);

  ExceptionCatcherFactory.$inject = ['logger'];

  function ExceptionCatcherFactory(logger) {
    var service = {
      catcher: catcher
    };

    return service;

    function catcher(message) {
      return function (reason) {
        logger.error(message, reason);
      };
    }
  }
})();
