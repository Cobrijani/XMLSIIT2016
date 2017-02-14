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
      {id: 'ROLE_PRIVATE_ADVERTISER', value: 'Private Advertiser'},
      {id: 'ROLE_COMPANY_ADVERTISER', value: 'Company Advertiser'},
      {id: 'ROLE_VERIFIER', value: 'Verifier'}
    ];

    vm.registerData = {role: vm.options[0].id};
  }
})();
