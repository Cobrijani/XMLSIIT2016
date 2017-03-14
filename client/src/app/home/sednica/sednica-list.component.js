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

  SednicaListController.$inject = ['$scope', 'GenericResource', 'exception', 'FileFactory', '$state'];

  function SednicaListController($scope, GenericResource, exception, FileFactory, $state) {
    var vm = this;

    vm.openSednica = openSednica;

    activate();


    function activate() {
      GenericResource.getEntities('sednice')
        .then(function (success) {
          vm.sednice = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function openSednica(sednica) {
      $state.go(sednica.id);
    }


  }
})();

