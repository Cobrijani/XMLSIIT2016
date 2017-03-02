(function () {
  'use strict';

  angular
    .module('app')
    .run(AuthRouterRun);

  AuthRouterRun.$inject = ['routerHelper'];

  function AuthRouterRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [
      {
        state: 'unauthorized',
        config: {
          url: '/unauthorized',
          templateUrl: 'app/unauthorized/unauthorized.html',
          controller: 'UnauthorizedController',
          controllerAs: 'vm'
        }
      }
    ];
  }
})();
