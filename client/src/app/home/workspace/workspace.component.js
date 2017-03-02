(function () {
  'use strict';

  angular
    .module('app')
    .component('workspaceComponent', {
      controller: workspaceController,
      controllerAs: 'vm',
      templateUrl: 'app/home/workspace/workspace.component.html',
      bindings: {}
    });

  workspaceController.$inject = ['$scope'];

  function workspaceController($scope) {
    var vm = this;
    //content
  }
})();
