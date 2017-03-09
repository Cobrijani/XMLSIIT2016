/**
 * Created by Arsa on 7/3/2017.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('amandmanCreateComponent', {
      controller: AmandmanCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/amandman/amandman-create.component.html',
      bindings: {}
    });

  AmandmanCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state'];

  function AmandmanCreateController($scope, GenericResource, exception, $state) {
    var vm = this;

    vm.createAmandman = createAmandman;
    //content

    function createAmandman() {
      GenericResource.postEntity('amandmani', vm.amandman, {'Content-Type': 'application/xml'})
        .then(function (success) {
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        })
    }
  }
})();
