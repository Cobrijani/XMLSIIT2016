/**
 * Created by Micko on 27-Dec-16.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .controller('HomeController', HomeController);

  HomeController.$inject = ['UserResource', 'exception', 'UserJwtResource', 'authManager'];

  /* @ngInject */
  function HomeController(UserResource, exception, UserJwtResource, authManager) {
    var vm = this;
    vm.title = 'HomeController';
    vm.user = null;
    vm.applications = [];
    vm.userId = "";

    activate();

    function activate() {
      if (authManager.isAuthenticated()) {
        vm.userId = UserJwtResource.getUserPayload().sub;
      }
      getUser();
    }

    function getUser() {
      UserResource.getUser(vm.userId)
        .then(function (user) {
          vm.user = user;
        })
        .catch(function (error) {
          exception.catcher('User error')(error);
        });
    }
  }

})();
