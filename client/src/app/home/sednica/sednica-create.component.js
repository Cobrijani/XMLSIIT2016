/**
 * Created by Micko on 04-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('sednicaCreateComponent', {
      controller: SednicaCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/sednica/sednica-create.component.html',
      bindings: {}
    });

  SednicaCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state', 'FileFactory'];

  function SednicaCreateController($scope, GenericResource, exception, $state, FileFactory) {
    var vm = this;
    vm.opened = false;
    vm.sednica = {akti:[], amandmani:[]};
    vm.idSelectedAkt = null;
    vm.addedAkts = [];
    vm.addedAmandmans = [];

    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    // vm.createSednica = createSednica;
    vm.open = open;
    vm.createNewSednica = createNewSednica;
    vm.setSelectedAkt = setSelectedAkt;
    vm.addAktToSednica = addAktToSednica;
    //content

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

    // function createSednica() {
    //   GenericResource.postEntity('sednice', vm.sednica, {'Content-Type': 'application/xml'})
    //     .then(function (success) {
    //       $state.go('main');
    //     })
    //     .catch(function (error) {
    //       exception.catcher(error);
    //     })
    // }

    function open() {
      vm.opened = true;
    }

    function createNewSednica(sednica) {
      sednica.datum = new Date(sednica.date.getFullYear(), sednica.date.getMonth(), sednica.date.getDate(),
      sednica.time.getHours(), sednica.time.getMinutes());
      delete sednica.time
      delete sednica.date
      console.log(sednica);
      console.log(sednica);
      GenericResource.postEntity('sednice', sednica, {'Content-Type': 'application/json'})
        .then(function (success) {
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        })
    }

    function getDetails(resources, id) {
      FileFactory.getDocumentAsArrayBuffer(resources, id, 'text/html')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'text/html')
        });
    }

    function getPdf(resources, id) {
      FileFactory.getDocumentAsArrayBuffer(resources, id, 'application/pdf')
        .then(function (success) {
          FileFactory.openFileInNewWindow(success.data, 'application/pdf');
        })
    }

    function setSelectedAkt(id) {
      vm.idSelectedAkt = id;
      //ucitati amandmane od tog akta
    }

    function addAktToSednica(akt, index) {
      vm.akti.splice(index, 1);
      vm.addedAkts.push(akt);
      vm.sednica.akti.push(akt.id);
    }
  }
})();

