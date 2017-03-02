/**
 * Created by Micko on 27-Dec-16.
 */
(function () {
  'use strict';

  angular
    .module('block.menu')
    .component('menuComponent', {
      controller: MenuController,
      controllerAs: 'vm',
      templateUrl: 'app/core/menu/menu.html',
      bindings: {}
    });

  MenuController.$inject = ['menuConfigurer', 'UserJwtResource', 'authManager'];

  function MenuController(menuConfigurer, UserJwtResource, authManager) {
    var vm = this;

    vm.isAuthenticated = isAuthenticated;
    vm.leftNav = menuConfigurer.getMenu();
    vm.rightNav = menuConfigurer.getMenu('right');

    activate();

    function activate() {
    }

    function isAuthenticated() {
      return authManager.isAuthenticated();
    }

  }
})();
