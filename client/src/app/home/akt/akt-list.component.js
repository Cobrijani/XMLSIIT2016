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
    vm.pageChanged = pageChanged;

    vm.pageOptions = {
      size: 5,
      page: 1,
      sort: null
    };

    activate();


    function pageChanged() {
      activate();
    }

    function activate() {
      getEntities(vm.pageOptions.size, vm.pageOptions.page - 1);
    }

    function getEntities(size, page) {
      GenericResource.getEntities('akti', {size: size, page: page})
        .then(function (success) {
          vm.akti = success;
          vm.pageOptions.page = vm.akti.number + 1; //counting starts from 1 on server from 0
          vm.pageOptions.size = vm.akti.size;
          vm.pageOptions.sort = vm.akti.sort;
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
