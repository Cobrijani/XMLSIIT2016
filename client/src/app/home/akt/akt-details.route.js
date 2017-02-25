(function () {
  'use strict';

  angular
    .module('app')
    .run(AktDetailsRouteRun);

  AktDetailsRouteRun.$inject = ['routerHelper'];

  function AktDetailsRouteRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [{
      state: 'akt-details',
      config: {
        url: '/akt/:id',
        template: '<akt-details-component></akt-details-component>',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();
