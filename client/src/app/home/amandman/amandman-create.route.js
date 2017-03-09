(function () {
  'use strict';

  angular
    .module('app')
    .run(AmandmanCreateRouteRun);

  AmandmanCreateRouteRun.$inject = ['routerHelper'];

  function AmandmanCreateRouteRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [{
      state: 'create-amandman',
      config: {
        url: '/create-amandman',
        template: '<amandman-create-component></amandman-create-component>',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();
