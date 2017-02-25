(function () {
  'use strict';

  angular
    .module('app')
    .run(RegisterRouteRun);

  RegisterRouteRun.$inject = ['routerHelper', 'roles', 'menuConfigurer', '$state', 'authManager'];

  function RegisterRouteRun(routerHelper, roles, menuConfigurer, $state, authManager) {
    routerHelper.configureStates(getStates(roles));
    menuConfigurer.configureMenus(getMenu($state, authManager), 'right');
  }

  function getMenu($state, authManager) {
    return [{
      order: 1,
      id: 'register',
      template: '<a ui-sref="register" ng-if="menuItem.isAuth()"><i class="fa fa-user"></i> Registracija</a>',
      isAuth: function () {
        return !authManager.isAuthenticated();
      }
    }];
  }

  function getStates() {
    return [{
      state: 'register',
      config: {
        url: '/register',
        templateUrl: 'app/register/register.html',
        controller: 'RegisterController',
        controllerAs: 'vm',
        data: {
          requiresNonLogin: true
        }
      }
    }];
  }

})();
