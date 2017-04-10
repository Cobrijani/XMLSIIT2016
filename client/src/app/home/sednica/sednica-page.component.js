/**
 * Created by Micko on 14-Mar-17.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('sednicaPageComponent', {
      controller: SednicaPageController,
      controllerAs: 'vm',
      templateUrl: 'app/home/sednica/sednica-page.component.html',
      bindings: {}
    });

  SednicaPageController.$inject = ['$scope', 'GenericResource', 'exception', '$state', 'FileFactory', '$stateParams', 'Restangular'];

  function SednicaPageController($scope, GenericResource, exception, $state, FileFactory, $stateParams, Restangular) {
    var vm = this;
    vm.opened = false;
    vm.sednica = {akti : [], amandmani : []};
    vm.idSelectedAkt = null;
    vm.votingDocuments = [];
    vm.votingDocument = null;
    vm.votingResults = {for: 0, against : 0, notVote : 0};
    vm.votedDocuments = [];


    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    vm.setSelectedAkt = setSelectedAkt;
    vm.putInVotingRow = putInVotingRow;
    vm.putToVote = putToVote;
    vm.voteFor = voteFor;
    vm.voteAgainst = voteAgainst;
    vm.closeVoting = closeVoting;

    //content

    activate();

    function activate() {
      GenericResource.getEntityByIdentifier('sednice', $stateParams.id)
        .then(function (success) {
          console.log(success);
          vm.sednica = success;
          vm.sednica.datum = new Date(vm.sednica.datum);
        })
        .catch(function (error) {
          exception.catcher('Sednica error')(error);
        });
      GenericResource.getEntities('sednice/'+ $stateParams.id + '/akti')
        .then(function (success) {
          console.log(success);
          vm.sednica.akti = success;
          for(var i = 0; i < vm.sednica.akti.length; i++){
            var akt = vm.sednica.akti[i];
            akt.type = 'akt';
            if(akt.state === 'inRow'){
              vm.votingDocuments.push(akt);
            }else if(akt.state === 'vote') {
              vm.votingDocument = akt;
            }else if(akt.state === 'voted'){
              vm.votedDocuments.push(akt);
            }
          }
          for(var i = 0; i < vm.sednica.akti.length; i++){
            if(vm.sednica.akti[i].state !== 'default'){
              vm.sednica.akti.splice(i, 1);
            }
          }
        })
        .catch(function (error) {
          exception.catcher('Akti error')(error);
        });
      GenericResource.getEntities('sednice/'+ $stateParams.id + '/amandmani')
        .then(function (success) {
          console.log(success);
          vm.sednica.amandmani = success;
          for(var i = 0; i < vm.sednica.amandmani.length; i++){
            vm.sednica.amandmani[i].selected = false;
            var am = vm.sednica.amandmani[i];
            am.type = 'am';
            if(am.state === 'inRow'){
              vm.votingDocuments.push(am);
            }else if(am.state === 'vote') {
              vm.votingDocument = am;
            }else if(am.state === 'voted'){
              vm.votedDocuments.push(am);
            }
          }
          for(var i = 0; i < vm.sednica.amandmani.length; i++){
            if(vm.sednica.amandmani[i].state !== 'default'){
              vm.sednica.amandmani.splice(i, 1);
            }
          }
        })
        .catch(function (error) {
          exception.catcher('Amandmani error')(error);
        });
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
      for(var i = 0; i < vm.sednica.amandmani.length; i++){
        if(vm.sednica.amandmani[i].aktId === id){
          vm.sednica.amandmani[i].selected = true;
        }else{
          vm.sednica.amandmani[i].selected = false;
        }
      }
    }

    function putInVotingRow(akt, $index) {
      console.log(akt);
      akt.state = 'inRow';
      var akt_restang = Restangular.copy(akt);
      console.log(akt_restang)
      akt_restang.route = 'akti';
      akt_restang.put();
      akt.type = 'akt';
      vm.votingDocuments.push(akt);
      vm.sednica.akti.splice($index, 1);
      for(var i = 0; i < vm.sednica.amandmani.length; i++){
        if(vm.sednica.amandmani[i].aktId === akt.id){
          var am = vm.sednica.amandmani[i];
          am.state = 'inRow';
          var am_restang = Restangular.copy(am);
          console.log(am_restang)
          am_restang.route = 'amandmani';
          am_restang.put();
          am.type = 'am';
          vm.votingDocuments.push(am);
        }
      }
      var amandmani = [];
      for(var i = 0; i < vm.sednica.amandmani.length; i++) {
        if (vm.sednica.amandmani[i].aktId !== akt.id) {
          amandmani.push(vm.sednica.amandmani[i]);
        }
      }
      vm.sednica.amandmani = amandmani;
      }

    function putToVote(document, $index) {
      document.state = 'vote';
      var doc_restang = Restangular.copy(document);
      console.log(doc_restang)
      if(document.type === 'akt'){
        doc_restang.route = 'akti';
      }else{
        doc_restang.route = 'amandmani';
      }
      doc_restang.put();

      vm.votingDocuments.splice($index, 1);
      vm.votingDocument = document;
    }

    function voteFor(dokument) {
      console.log('voteFor');
    }

    function voteAgainst() {
      console.log('voteAgainst');
    }

    function closeVoting() {
      console.log('close voting');
      vm.votingDocument.for = vm.votingResults.for;
      vm.votingDocument.against = vm.votingResults.against;
      vm.votingDocument.notVote = vm.votingResults.notVote;
      vm.votingDocument.state = 'voted';
      vm.votedDocuments.push(vm.votingDocument);
      var doc_restang = Restangular.copy(vm.votingDocument);
      console.log(doc_restang)
      if(vm.votingDocument.type === 'akt'){
        doc_restang.route = 'akti';
      }else{
        doc_restang.route = 'amandmani';
      }
      doc_restang.put();
      vm.votingDocument = null;
    }
  }
})();

