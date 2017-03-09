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

  AktListController.$inject = ['$scope', 'GenericResource', 'exception', 'FileFactory', '$sce'];

  function AktListController($scope, GenericResource, exception, FileFactory, $sce) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.getPdf = getPdf;

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
      FileFactory.getDocumentAsArrayBuffer('akti', id, 'text/html')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'text/html')

        });
    }

    function getPdf(id) {
      FileFactory.getDocumentAsArrayBuffer('akti', id, 'application/pdf')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'application/pdf');
        })
    }

  }
})();
