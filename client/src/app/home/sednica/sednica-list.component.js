/**
 * Created by Micko on 03-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('sednicaListComponent', {
      controller: SednicaListController,
      controllerAs: 'vm',
      templateUrl: 'app/home/sednica/sednica-list.component.html',
      bindings: {}
    });

  SednicaListController.$inject = ['GenericResource', 'exception', 'UserJwtResource', 'roles'];

  function SednicaListController(GenericResource, exception, UserJwtResource, roles) {
    var vm = this;

    activate();

    vm.notPredsednik = UserJwtResource.getUserPayload().auth !== roles.predsednik;


    function activate() {
      GenericResource.getEntities('sednice')
        .then(function (success) {
          vm.sednice = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }
  }
})();

