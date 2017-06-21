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

  AmandmanListController.$inject = ['$scope', '_', 'GenericResource', 'exception', 'FileFactory', 'UserJwtResource', 'roles', '$state', '$log'];

  function AmandmanListController($scope, _, GenericResource, exception, FileFactory, UserJwtResource, roles, $state, $log) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    vm.pageChanged = pageChanged;
    vm.openDateFrom = openDateFrom;
    vm.openDateTo = openDateTo;
    vm.search = search;
    vm.reset = reset;
    vm.delete = deleteAmandman;
    vm.getAktName = getAktName;
    vm.getAktDetails = getAktDetails;

    vm.amandmanStates = [];
    vm.selectedState = "";

    vm.akti = [];
    vm.selectedAktId = "";

    vm.isOdbornik = UserJwtResource.getUserPayload().auth === roles.odbornik || UserJwtResource.getUserPayload().auth === roles.predsednik;
    vm.username = UserJwtResource.getUserPayload().sub;

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


    ///////////////////////////////////////////////////////////

    activate();

    function activate() {
      getEntities({size: vm.pageOptions.size, page: vm.pageOptions.page - 1, self: vm.self});
    }

    function search() {
      var q = {
        size: vm.pageOptions.size,
        page: vm.pageOptions.page - 1,
        q: vm.searchText,
        self: vm.self
      };
      if (vm.dateFrom) {
        q.from = vm.dateFrom.getTime() / 1000;
      }
      if (vm.dateTo) {
        q.to = vm.dateTo.getTime() / 1000;
      }

      if (vm.selectedState) {
        q.state = vm.selectedState;
      }

      if (vm.selectedAktId) {
        q.aktId = vm.selectedAktId;
      }

      getEntities(q);
    }

    function reset() {
      vm.dateFrom = '';
      vm.dateTo = '';
      vm.searchText = '';
      vm.self = false;
      vm.selectedState = "";
      vm.selectedAktId = "";
      search();
    }

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

      GenericResource.getEntities('amandmanStates')
        .then(function (success) {
          vm.amandmanStates = [""].concat(success);
        })
        .catch(function (error) {
          $log.error(error.data.message);
        });
    }

    function getEntities(params) {
      GenericResource.getEntities('amandmani', params)
        .then(function (success) {
          vm.amandmani = success;
          vm.pageOptions.page = vm.amandmani.number + 1; //counting starts from 1 on server from 0
          vm.pageOptions.size = vm.amandmani.size;
          vm.pageOptions.sort = vm.amandmani.sort;
        })
        .catch(function (error) {
          exception.catcher(error);
        });

      GenericResource.getEntities('akti')
        .then(function (success) {
          vm.akti = success.content;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function deleteAmandman(id){
      GenericResource.deleteEntity('amandmani', id)
        .then(function (success) {
          $state.reload();
          toastr.success("Amandman uspesno povucen");
          $log.info("Successfully deleted");
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function getAktName(aktId){
      return _.result(_.find(vm.akti, { 'id': aktId }), 'name');
    }


    function getDetails(id) {
      FileFactory.getDocumentAsArrayBuffer('amandmani', id, 'text/html')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'text/html')

        });
    }

    function getAktDetails(id) {
      FileFactory.getDocumentAsArrayBuffer('akti', id, 'text/html')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'text/html');
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
