(function () {
  'use strict';

  angular
    .module('app')
    .config(AngularLocalStorageConfiguration);

  AngularLocalStorageConfiguration.$inject = ['localStorageServiceProvider', 'authProperties'];

  function AngularLocalStorageConfiguration(localStorageServiceProvider, authProperties) {
    localStorageServiceProvider.setPrefix(authProperties.STORAGE_PREFIX_NAME);
  }
})();
