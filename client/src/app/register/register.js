(function () {
  'use strict';

  angular
    .module('app')
    .controller('RegisterController', RegisterController);

  RegisterController.$inject = ['UserResource', 'exception', '$state', 'logger'];

  function RegisterController(UserResource, exception, $state, logger) {
    var vm = this;

    vm.register = register;

    function register(registerDto) {
      UserResource.register(registerDto).then(function () {
        $state.go('login');
        logger.success('successfully registered');
      }).catch(function () {
        //
      });
    }
  }
})();
