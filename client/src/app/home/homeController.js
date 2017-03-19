/**
 * Created by Micko on 27-Dec-16.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .controller('HomeController', HomeController);

  HomeController.$inject = ['GenericResource', 'exception', 'UserJwtResource', 'authManager'];

  /* @ngInject */
  function HomeController(GenericResource, exception, UserJwtResource, authManager) {
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
      GenericResource.getEntityByIdentifier('korisnici', vm.userId)
        .then(function (user) {
          vm.user = user;
        })
        .catch(function (error) {
          exception.catcher('User error')(error);
        });
    }
  }

})();
