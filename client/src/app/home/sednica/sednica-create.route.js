/**
 * Created by Micko on 04-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .run(SednicaCreateRouteRun);

  SednicaCreateRouteRun.$inject = ['routerHelper'];

  function SednicaCreateRouteRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [{
      state: 'create-sednica',
      config: {
        url: '/create-sednica',
        template: '<sednica-create-component></sednica-create-component>',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();

