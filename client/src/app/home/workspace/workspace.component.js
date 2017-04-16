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

  workspaceController.$inject = [];

  function workspaceController() {
    var vm = this;
    //content
  }
})();
