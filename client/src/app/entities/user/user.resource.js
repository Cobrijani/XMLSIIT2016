(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('UserResource', UserResource);

  UserResource.$inject = ['Users', 'exception', '$q', 'Restangular'];

  function UserResource(Users, exception, $q, Restangular) {
    return {
      register: register,
      getUser: getUser,
      getUsers: getUsers,
      putUser: putUser,
      getUserAuthorities: getUserAuthorities
    };

    ////////////////
    function register(registerDto) {
      var deferred = $q.defer();
      Users.post(registerDto).then(function (success) {
        deferred.resolve(success);
      }).catch(function (error) {
        deferred.reject(error);
        exception.catcher(error.data)(error);
      });

      return deferred.promise;
    }

    function getUser(userId) {
      return Restangular.one("users", userId).get().then(function (user) {
        return user;
      });
    }

    function getUsers(params) {
      return Restangular.all("users").customGET("", params).then(function (users) {
        return users;
      }).catch(function (error) {
        exception.catcher(error.statusText)(error);
      });
    }

    function putUser(userId, user) {
      return Restangular.all("users").one(userId.toString()).customPUT(user).then(
        function (user) {
          return user;

        },
        function () {
          return undefined;
        });
    }

    function getUserAuthorities(userId) {
      return Restangular.one("users", userId).customGET("authorities").then(function (authorities) {
        return authorities;
      });
    }

  }
})();
