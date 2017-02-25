/**
 * Created by SBratic on 2/24/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('aktDetailsComponent', {
      controller: AktDetailsController,
      controllerAs: 'vm',
      templateUrl: 'app/home/akt/akt-details.component.html',
      bindings: {}
    });

  AktDetailsController.$inject = ['$scope'];

  function AktDetailsController($scope) {
    var vm = this;
    //content
  }
})();
