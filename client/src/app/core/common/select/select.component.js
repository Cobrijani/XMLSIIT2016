(function () {
  'use strict';

  angular
    .module('block.common')
    .component('selectComponent', selectComponent());

  /* @ngInject */
  function selectComponent() {
    var component = {
      templateUrl: 'app/core/common/select/select.component.html',
      controller: Controller,
      controllerAs: 'vm',
      require: {
        selectedModel: '^ngModel'
      },
      bindings: {
        allData: '=',
        label: '@',
        selectedModel: '=ngModel',
        name: '@',
        id: '@'
      }
    };

    return component;
  }

  Controller.$inject = [];

  /* @ngInject */
  function Controller() {
    var vm = this;

    vm.$onInit = function () {
      vm.selectedModel = vm.allData[0].id;
    };
  }
})();
