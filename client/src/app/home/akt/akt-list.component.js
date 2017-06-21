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

  AktListController.$inject = ['$scope', 'toastr', '$state', 'GenericResource',
    'exception', 'FileFactory', '$sce', '$log', 'UserJwtResource', 'roles', '$uibModal'
  ];

  function AktListController($scope, toastr, $state, GenericResource, exception,
    FileFactory, $sce, $log, UserJwtResource, roles, $uibModal) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    vm.pageChanged = pageChanged;
    vm.openDateFrom = openDateFrom;
    vm.openDateTo = openDateTo;
    vm.search = search;
    vm.reset = reset;
    vm.deleteAkt = deleteAkt;
    vm.openPrompt = openPrompt;

    vm.aktStates = [];
    vm.selectedState = "";

    vm.isOdbornik = UserJwtResource.getUserPayload().auth === roles.odbornik ||
      UserJwtResource.getUserPayload().auth === roles.predsednik;
    vm.login = UserJwtResource.getUserPayload().sub;

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

    function openPrompt(aktId) {
      var modal = $uibModal.open({
        animation: true,
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: 'app/home/akt/delete-akt-modal.html',
        controller: 'DeleteAktModalController',
        controllerAs: 'vm',
        resolve: {
          akt: function () {
            return aktId;
          }
        }
      });
      modal.result.then(function (id) {
        deleteAkt(id);
      }, function () {
        $log.info('Modal dismissed');
      });
    }

    function deleteAkt(aktId) {
      GenericResource.deleteEntity('akti', aktId)
        .then(function () {
          $state.reload();
          toastr.success("Akt uspesno povucen");
          $log.info("Successfully deleted");
        })
        .catch(function (error) {
          $log.error("Error deleting akt with id", aktId, ". Error message: ", error.data.message);
          toastr.error("Greska pri povlacenju akta");
        });
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

      getEntities(q);
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
      vm.dateFromPopup = {
        opened: true
      };
    }

    function openDateTo() {
      vm.dateToPopup = {
        opened: true
      };
    }

    function pageChanged() {
      activate();
    }

    function activate() {
      getEntities({
        size: vm.pageOptions.size,
        page: vm.pageOptions.page - 1,
        self: vm.self
      });

      GenericResource.getEntities('aktStates')
        .then(function (success) {
          vm.aktStates = [""].concat(success);
        })
        .catch(function (error) {
          $log.error(error.data.message);
        });
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
          FileFactory.openFileInNewWindow(success.data, 'text/html');
        });
    }

    function getPdf(id) {
      FileFactory.getDocumentAsArrayBuffer('akti', id, 'application/pdf')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'application/pdf');
        });
    }
  }
})();
