(function() {
  'use strict';

  angular
    .module('app')
    .run(AktCreateRouteRun);

  AktCreateRouteRun.$inject = ['routerHelper'];

  function AktCreateRouteRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [{
      state: 'create-akt',
      config: {
        url: '/create-akt',
        template: '<akt-create-component></akt-create-component>',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();
