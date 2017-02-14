(function () {
  'use strict';

  angular
    .module('app')
    .controller('LoginController', LoginController);
  LoginController.$inject = ['UserJwtResource', '$state', 'exception'];

  function LoginController(UserJwtResource, $state, exception) {
    var vm = this;

    vm.login = login;

    //////////////////////////

    function login(credentials) {
      UserJwtResource.authenticate({
        username: credentials.email,
        password: credentials.password,
        isRememberMe: credentials.rememberMe
      }).then(function () {
        $state.go('main');
      }).catch(function (error) {
        exception.catcher('Invalid credentials')(error);
        $state.go('login');
      });
    }
  }
})();
