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

  SednicaPageController.$inject = ['$scope', 'GenericResource', 'exception', '$state', 'FileFactory', '$stateParams', 'Restangular', 'toastr'];

  function SednicaPageController($scope, GenericResource, exception, $state, FileFactory, $stateParams, Restangular, toastr) {
    var vm = this;
    vm.opened = false;
    vm.sednica = {akti : [], amandmani : []};
    vm.idSelectedAkt = null;
    vm.votingDocuments = [];
    vm.votingDocument = null;
    vm.votingResults = {for: 0, against : 0, notVote : 0};
    vm.votedDocuments = [];
    vm.defaultAkts = [];
    vm.defaultAmandmans = [];
    vm.userVote = false;


    vm.getDetails = getDetails;
    vm.getPdf = getPdf;
    vm.setSelectedAkt = setSelectedAkt;
    vm.putInVotingRow = putInVotingRow;
    vm.putToVote = putToVote;
    vm.vote = vote;
    vm.closeVoting = closeVoting;
    vm.acceptDeclineDocument = acceptDeclineDocument;
    vm.selectAktsAmandmands = selectAktsAmandmands;

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
            }else{
              vm.defaultAkts.push(akt);
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
            }else{
              vm.defaultAmandmans.push(am);
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
      for(var i = 0; i < vm.defaultAmandmans.length; i++){
        if(vm.defaultAmandmans[i].aktId === id){
          vm.defaultAmandmans[i].selected = true;
        }else{
          vm.defaultAmandmans[i].selected = false;
        }
      }
    }

    function putInVotingRow(akt, $index) {
      console.log(akt);
      akt.state = 'inRow';
      akt.selected = false;
      var akt_restang = Restangular.copy(akt);
      console.log(akt_restang)
      akt_restang.route = 'akti';
      akt_restang.forVote = null;
      akt_restang.against = null;
      akt_restang.put();
      akt.type = 'akt';
      vm.votingDocuments.push(akt);
      vm.defaultAkts.splice($index, 1);
      for(var i = 0; i < vm.defaultAmandmans.length; i++){
        if(vm.defaultAmandmans[i].aktId === akt.id){
          var am = vm.defaultAmandmans[i];
          am.state = 'inRow';
          var am_restang = Restangular.copy(am);
          console.log(am_restang)
          am_restang.forVote = null;
          am_restang.against = null;
          am_restang.route = 'amandmani';
          am_restang.put();
          am.type = 'am';
          vm.votingDocuments.push(am);
        }
      }
      var amandmani = [];
      for(var i = 0; i < vm.defaultAmandmans.length; i++) {
        if (vm.defaultAmandmans[i].aktId !== akt.id) {
          amandmani.push(vm.defaultAmandmans[i]);
        }
      }
      vm.defaultAmandmans = amandmani;
      }

    function putToVote(document, $index) {
      vm.userVote = false;
      document.state = 'vote';
      var doc_restang = Restangular.copy(document);
      doc_restang.forVote = null;
      doc_restang.against = null;
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

    function vote(document, forBool) {
      console.log('vote');
      console.log(document);
      vm.userVote = true;
      var doc_restang = Restangular.copy(document);
      if(forBool){
        doc_restang.against = null;
        document.forVote++;
      }else{
        doc_restang.forVote = null;
        document.against++;
      }
      console.log(doc_restang);
      if(vm.votingDocument.type === 'akt'){
        doc_restang.route = 'akti';
      }else{
        doc_restang.route = 'amandmani';
      }
      doc_restang.put();
    }


    function closeVoting() {
      console.log('close voting');
      vm.votingDocument.state = 'voted';
      vm.votedDocuments.push(vm.votingDocument);
      var doc_restang = Restangular.copy(vm.votingDocument);
      doc_restang.forVote = null;
      doc_restang.against = null;
      if(vm.votingDocument.type === 'akt'){
        doc_restang.route = 'akti';
      }else{
        doc_restang.route = 'amandmani';
      }
      doc_restang.put();
      vm.votingDocument = null;
    }

    function acceptDeclineDocument(document, status) {
      console.log(status);
      if(document.type === 'akt'){
        for (var i = 0; i < vm.votedDocuments.length; i++){
          if(vm.votedDocuments[i].aktId == document.id && vm.votedDocuments[i].result === 'default'){
            toastr.error('Prvo usvojite ili odbacite sve amandmane tog akta, a nakon toga mozete da obradite akt', 'Prvo amandmane sve!')
            return;
          }
        }
      }
      document.result = status;
      var doc_restang = Restangular.copy(document);
      if(document.type === 'akt'){
        doc_restang.route = 'akti';
      }else{
        doc_restang.route = 'amandmani';
      }
      doc_restang.forVote = null;
      doc_restang.against = null;
      doc_restang.put();
    }

    function selectAktsAmandmands(doc, data) {
      for(var i = 0; i < data.length; i++){
        if(data[i].id === doc.id || data[i].aktId === doc.id || data[i].id === doc.aktId){
          data[i].selected = true;
        }else{
          data[i].selected = false;
        }
      }
    }
  }
})();

