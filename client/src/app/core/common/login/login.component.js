(function () {
  'use strict';

  angular
    .module('block.common')
    .component('loginComponent', {
      controller: LoginController,
      controllerAs: 'vm',
      templateUrl: 'app/core/common/login/login.component.html',
      bindings: {
        onLogin: '&'
      }
    });

  LoginController.$inject = [];

  function LoginController() {
    var vm = this;
    vm.credentials = {rememberMe: false};
  }
})();
