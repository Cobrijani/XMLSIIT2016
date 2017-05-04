/**
 * Created by SBratic on 2/24/2017.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('aktCreateComponent', {
      controller: AktCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/akt/akt-create.component.html',
      bindings: {}
    });

  AktCreateController.$inject = ['$scope', 'GenericResource', 'exception', '$state', 'AktSpecification', 'AktIdsIncrementerService'];

  function AktCreateController($scope, GenericResource, exception, $state, AktSpecification, AktIdsIncrementerService) {
    var vm = this;

    vm.validation = {
      message: ''
    };
    activate();
    vm.createAkt = createAkt;
    vm.radioBtnChange = radioBtnChange;
    vm.docMode = 'laic';
    radioBtnChange(vm.docMode);
    vm.spec = AktSpecification.akt;

    //content

    function activate() {
      vm.akt = "<akt:akt xmlns:akt='http://parlament.gov.rs/rs.ac.uns.ftn.model.akt'" +
        " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'" +
        " xmlns:meta='http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata'" +
        " xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'" +
        " xmlns='http://www.w3.org/ns/rdfa#' > <akt:zaglavlje>" +
        "<meta:naziv datatype='xs:string' property='pred:imeDokumenta'></meta:naziv>" +
        "</akt:zaglavlje><akt:preambula></akt:preambula></akt:akt>";
      $scope.$on('validation:akt', function (scope, data) {
        vm.validation = data;
      });

    }

    function radioBtnChange(val) {
      if (val === 'raw') {
        vm.xonomyMode = false;
        vm.akt = Xonomy.harvest();
      } else {
        vm.xonomyMode = true;
      }
    }

    function createAkt() {
      GenericResource.postEntity('akti', Xonomy.harvest(), {
        'Content-Type': 'application/xml'
      })
        .then(function () {
          AktIdsIncrementerService.resetAllCounters();
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }
  }
})();
