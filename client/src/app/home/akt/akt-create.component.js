/**
 * Created by SBratic on 2/24/2017.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('aktCreateComponent', {
      controller: AktCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/akt/akt-create.component.html',
      bindings: {}
    });

  AktCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state'];

  function AktCreateController($scope, GenericResource, exception, $state) {
    var vm = this;

    vm.createAkt = createAkt;
    vm.radioBtnChange = radioBtnChange;
    vm.docMode = 'laic';
    radioBtnChange(vm.docMode);
    vm.spec = aktSpec;

    //content

    function radioBtnChange(val) {
      if ('raw' === val) {
        vm.xonomyMode = false;
        vm.akt = Xonomy.harvest();
      } else {
        vm.xonomyMode = true;
      }
    }

    function createAkt() {
      GenericResource.postEntity('akti', vm.akt, {'Content-Type': 'application/xml'})
        .then(function (success) {
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        })
    }
  }
})();
