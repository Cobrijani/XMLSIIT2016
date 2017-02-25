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

  AktListController.$inject = ['$scope', 'GenericResource', 'exception'];

  function AktListController($scope, GenericResource, exception) {
    var vm = this;

    activate();


    function activate() {
      GenericResource.getEntities('aktovi')
        .then(function (success) {
          vm.akti = success;
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

  }
})();
