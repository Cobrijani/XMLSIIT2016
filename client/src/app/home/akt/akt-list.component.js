/**
 * Created by SBratic on 2/24/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('aktListComponent', {
      controller: AktListController,
      controllerAs: 'vm',
      templateUrl: 'app/home/akt/akt-list.component.html',
      bindings: {}
    });

  AktListController.$inject = ['$scope', 'GenericResource', 'exception', 'FileFactory'];

  function AktListController($scope, GenericResource, exception, FileFactory) {
    var vm = this;
    vm.getDetails = getDetails;

    activate();


    function activate() {
      GenericResource.getEntities('akti')
        .then(function (success) {
          vm.akti = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function getDetails(id) {
      FileFactory.getDocumentInFormat('akti', id, 'text/html');
    }

  }
})();
