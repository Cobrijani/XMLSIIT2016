(function () {
  'use strict';

  angular
    .module('app')
    .run(LoginRouteRun);

  LoginRouteRun.$inject = ['routerHelper', 'roles', 'menuConfigurer', '$state', 'authManager', 'UserJwtResource'];

  function LoginRouteRun(routerHelper, roles, menuConfigurer, $state, authManager, UserJwtResource) {
    routerHelper.configureStates(getStates(roles));
    menuConfigurer.configureMenus(getMenu($state, authManager, UserJwtResource), 'right');
  }

  function getMenu($state, authManager, UserJwtResource) {
    return [{
      order: 2,
      id: 'login',
      template: '<a ng-if="menuItem.isAuth()" ui-sref="login"><i class="fa fa-sign-in"></i> Uloguj se</a> ',
      isAuth: function () {
        return !authManager.isAuthenticated();
      }
    }, {
      order: 3,
      id: 'logout',
      onClick: function () {
        UserJwtResource.logout();
      },
      template: '<a ng-if="menuItem.isAuth()" href ng-click="menuItem.onClick()"><i class="fa fa-sign-out"></i> Izloguj se</a>',
      isAuth: function () {
        return authManager.isAuthenticated();
      }
    }];
  }

  function getStates() {
    return [{
      state: 'login',
      config: {
        url: '/login',
        templateUrl: 'app/login/login.html',
        controller: 'LoginController',
        controllerAs: 'vm',
        data: {
          requiresNonLogin: true
        }
      }
    }];
  }
})();
