(function () {
  'use strict';

  angular
    .module('app')
    .run(AppRun);

  AppRun.$inject = ['authManager', '$log'];

  function AppRun(authManager, $log) {
    authManager.activateAuth();
    $log.info('Auth status: ', authManager.isAuthenticated() ? 'logged in' :
      'logged out');
  }
})();
