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

  AktCreateController.$inject = ['$scope', 'GenericResource', 'exception'];

  function AktCreateController($scope, GenericResource, exception) {
    var vm = this;

    vm.createAkt = createAkt;
    //content

    function createAkt(){
      GenericResource.postEntity('aktovi', vm.akt, {'Content-Type': 'application/xml'})
      .then(function (success) {
        console.log(success);
      })
      .catch(function (error) {
        exception.catcher(error);
      })
    }
  }
})();
