/**
 * Created by Micko on 25-Jan-17.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .component('applicationsComponent', {
      controller: ApplicationsController,
      controllerAs: 'vm',
      templateUrl: 'app/home/applications.component.html',
      bindings: {
        user: '=',
        applications: '='
      }
    });

  ApplicationsController.$inject = ['UserJwtResource', 'ApplicationResource', 'exception', '_', 'authManager', 'UserResource'];

  function ApplicationsController(UserJwtResource, ApplicationResource, exception, _, authManager, UserResource) {
    var vm = this;
    vm.title = 'ApplicationsController';
    vm.userId = "";

    vm.removeApp = removeApp;
    vm.$onInit = function () {
      activate();
    };

    function activate() {
      if (authManager.isAuthenticated()) {
        vm.userId = UserJwtResource.getUserPayload().sub;
        getApplications();
      }

    }

    function getApplications() {
      UserResource.getUserApplications(vm.userId)
        .then(function (success) {
          vm.applications = success;
        });
    }

    function removeApp(appId) {
      ApplicationResource.deleteApplication(appId)
        .then(function () {
          getApplications();
        })
        .catch(function (error) {
          exception.catcher(error.statusText)(error);
        });
    }

  }

})();
