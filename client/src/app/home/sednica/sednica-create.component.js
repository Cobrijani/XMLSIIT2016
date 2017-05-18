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

  SednicaCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state', 'FileFactory', 'toastr'];

  function SednicaCreateController($scope, GenericResource, exception, $state, FileFactory, toastr) {
    var vm = this;
    vm.opened = false;
    vm.sednica = {akti:[], amandmani:[]};
    vm.idSelectedAkt = null;
    vm.addedAkts = [];
    vm.addedAmandmans = [];
    vm.akti = [];
    vm.amandmani = [];

    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    // vm.createSednica = createSednica;
    vm.open = open;
    vm.createNewSednica = createNewSednica;
    vm.setSelectedAkt = setSelectedAkt;
    vm.addAktToSednica = addAktToSednica;
    vm.addAmandmanToSednica = addAmandmanToSednica;
    //content

    activate();

    function activate() {
      GenericResource.getEntities('akti')
        .then(function (success) {
          vm.akti = success.content;
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
      GenericResource.getEntities('akti/'+id+'/amandmani')
        .then(function (success) {
          vm.amandmani = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function addAktToSednica(akt, index) {
      vm.akti.splice(index, 1);
      vm.addedAkts.push(akt);
      vm.sednica.akti.push(akt.id);
    }

    function addAmandmanToSednica(amandman, index) {
      for(var i = 0; i < vm.akti.length; i++){
        if(vm.akti[i].id === vm.idSelectedAkt){
          toastr.error('Prvo dodajte zeljeni akt pa nakon toga amandmane za taj akt.', 'Prvo akt!')
          return;
        }
      }
      amandman.aktId = vm.idSelectedAkt;
      vm.amandmani.splice(index, 1);
      vm.addedAmandmans.push(amandman);
      vm.sednica.amandmani.push(amandman.id);
    }
  }
})();

