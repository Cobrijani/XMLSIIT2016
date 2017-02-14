(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('Users', Users);

  Users.$inject = ['Restangular'];

  function Users(Restangular) {
    return Restangular.service('users');
  }
})();
