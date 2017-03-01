/**
 * Created by SBratic on 2/24/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('aktDetailsComponent', {
      controller: AktDetailsController,
      controllerAs: 'vm',
      templateUrl: 'app/home/akt/akt-details.component.html',
      bindings: {}
    });

  AktDetailsController.$inject = ['$scope', '$stateParams', 'GenericResource', 'FileFactory'];

  function AktDetailsController($scope, $stateParams, GenericResource, FileFactory) {
    var vm = this;
    //content
    activate();
    vm.params = $stateParams;

    function activate() {
      getAkt();
    }

    function getAkt() {
      GenericResource.getEntityByIdentifier('akti', $stateParams.id, {'Accept': 'text/html'})
        .then(function (success) {
          vm.akt = success;
        })
        .catch(function (error) {
          vm.akt = "";
        });
    }
  }
})();
