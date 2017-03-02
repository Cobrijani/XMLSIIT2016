(function () {
  'use strict';

  angular
    .module('block.auth', [
      'block.router',
      'LocalStorageModule',
      'angular-jwt'
    ]);
})();
