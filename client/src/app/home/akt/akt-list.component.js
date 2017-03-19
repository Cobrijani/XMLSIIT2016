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

  AktListController.$inject = ['$scope', 'GenericResource', 'exception', 'FileFactory', '$sce', '$log', 'UserJwtResource', 'roles'];

  function AktListController($scope, GenericResource, exception, FileFactory, $sce, $log, UserJwtResource, roles) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    vm.pageChanged = pageChanged;
    vm.openDateFrom = openDateFrom;
    vm.openDateTo = openDateTo;
    vm.search = search;
    vm.reset = reset;

    vm.canEdit = UserJwtResource.getUserPayload().auth !== roles.gradjanin;

    vm.pageOptions = {
      size: 5,
      page: 1,
      sort: null
    };

    vm.dateOptions = {
      formatYear: 'yy',
      startingDay: 1
    };

    vm.dateFormat = "dd-MM-yyyy";
    /////////////////////


    function search() {
      if (vm.dateFrom) {
        var from = vm.dateFrom.getTime() / 1000;
      }
      if (vm.dateTo) {
        var to = vm.dateTo.getTime() / 1000;
      }
      getEntities({
        size: vm.pageOptions.size,
        page: vm.pageOptions.page - 1,
        q: vm.searchText,
        from: from,
        to: to,
        self: vm.self
      });
    }

    function reset() {
      vm.dateFrom = '';
      vm.dateTo = '';
      vm.searchText = '';
      vm.self = false;
      search();
    }

    activate();


    function openDateFrom() {
      vm.dateFromPopup = {opened: true}
    }

    function openDateTo() {
      vm.dateToPopup = {opened: true}
    }

    function pageChanged() {
      activate();
    }

    function activate() {
      getEntities({size: vm.pageOptions.size, page: vm.pageOptions.page - 1, self: vm.self});
    }

    function getEntities(params) {
      GenericResource.getEntities('akti', params)
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
})
();
