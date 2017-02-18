(function () {
  'use strict';

  angular
    .module('block.common')
    .component('registerComponent', {
      controller: RegisterController,
      controllerAs: 'vm',
      templateUrl: 'app/core/common/register/register.component.html',
      bindings: {
        onRegister: '&'
      }
    });

  RegisterController.$inject = [];

  function RegisterController() {
    var vm = this;
    vm.options = [
      {id: 'gradjanin', value: 'Gradjanin'},
      {id: 'odbornik', value: 'Odbornik'},
      {id: 'predsednik', value: 'Predsednik skupstine'}
    ];

    vm.registerData = {uloga: vm.options[0].id};
  }
})();
