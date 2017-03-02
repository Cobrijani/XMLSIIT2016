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
    //content

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
