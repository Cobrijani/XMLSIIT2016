(function () {
  'use strict';

  angular
    .module('app')
    .run(HomeRouteRun);

  HomeRouteRun.$inject = ['routerHelper', 'roles', 'menuConfigurer', '$state', 'authManager'];

  function HomeRouteRun(routerHelper, roles, menuConfigurer, $state, authManager) {
    routerHelper.configureStates(getStates(roles), '/unauthorized');
    menuConfigurer.configureMenus(getMenu($state, authManager), 'left');
  }

  function getMenu($state, authManager) {
    return [{
      order: 1,
      id: 'main',
      template: '<a ui-sref="main" ng-if="menuItem.isAuth()"><i class="fa fa-home"></i></a>',
      isAuth: function () {
        return authManager.isAuthenticated();
      }
    }];
  }

  function getStates() {
    return [{
      state: 'main',
      config: {
        url: '/',
        templateUrl: 'app/home/home.html',
        controller: 'HomeController',
        controllerAs: 'vm',
        data: {
          requiresLogin: true
        }
      }
    }];
  }
})();
