/**
 * Created by Arsa on 3/7/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('amandmanListComponent', {
      controller: AmandmanListController,
      controllerAs: 'vm',
      templateUrl: 'app/home/amandman/amandman-list.component.html',
      bindings: {}
    });

  AmandmanListController.$inject = ['$scope', 'GenericResource', 'exception', 'FileFactory'];

  function AmandmanListController($scope, GenericResource, exception, FileFactory) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.getPdf = getPdf;

    activate();


    function activate() {
      GenericResource.getEntities('amandmani')
        .then(function (success) {
          vm.amandmani = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function getDetails(id) {
      FileFactory.getDocumentAsArrayBuffer('amandmani', id, 'text/html')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'text/html')

        });
    }

    function getPdf(id) {
      FileFactory.getDocumentAsArrayBuffer('amandmani', id, 'application/pdf')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'application/pdf');
        })
    }

  }
})();
