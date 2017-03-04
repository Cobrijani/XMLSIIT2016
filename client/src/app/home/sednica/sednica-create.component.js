/**
 * Created by Micko on 04-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('sednicaCreateComponent', {
      controller: SednicaCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/sednica/sednica-create.component.html',
      bindings: {}
    });

  SednicaCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state'];

  function SednicaCreateController($scope, GenericResource, exception, $state) {
    var vm = this;

    vm.createSednica = createSednica;
    //content

    function createSednica() {
      GenericResource.postEntity('sednice', vm.sednica, {'Content-Type': 'application/xml'})
        .then(function (success) {
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        })
    }
  }
})();

