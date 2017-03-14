/**
 * Created by Micko on 14-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .run(SednicaPageRouteRun);

  SednicaPageRouteRun.$inject = ['routerHelper'];

  function SednicaPageRouteRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [{
      state: 'sednica-page',
      config: {
        url: '/sednica-page/{id}',
        template: '<sednica-page-component></sednica-page-component>',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();

