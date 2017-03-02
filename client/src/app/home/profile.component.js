/**
 * Created by Micko on 25-Jan-17.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('profileComponent', {
      controller: ProfileController,
      controllerAs: 'vm',
      templateUrl: 'app/home/profile.component.html',
      bindings: {
        user: '=',
        applications: '='
      }
    });

  ProfileController.$inject = ['$filter', 'UserJwtResource', 'exception', '_', 'authManager', 'logger', '$uibModal'];

  function ProfileController($filter, UserJwtResource, exception, _, authManager, logger, $uibModal) {
    var vm = this;
    vm.title = 'ProfileController';
    vm.$onInit = function () {
      activate();
    };

    vm.openNewApplicationModal = openNewApplicationModal;

    function activate() {
    }

    function openNewApplicationModal() {
      var modalInstance = $uibModal.open({
        templateUrl: 'app/application/applicationModal.html',
        controller: 'ApplicationModalController as vm',
        resolve: {
          edit: function () {
            return false;
          },
          application: function () {
            return null;
          }
        }
      });

      modalInstance.result.then(function (application) {
        vm.applications.push(application);
      }, function () {
      });
    }
  }

})();

