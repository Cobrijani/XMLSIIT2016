(function () {
  'use strict';

  angular
    .module('app')
    .factory('UserJwtResource', UserJwtResource);

  UserJwtResource.$inject = ['$http', '$rootScope', 'TokenService',
    'authProperties', '$log', '$q', 'jwtHelper', 'authManager', '_', '$state'
  ];

  function UserJwtResource($http, $rootScope, TokenService,
                           authProperties, $log, $q, jwtHelper, authManager, _, $state) {
    //content

    return {
      authenticate: authenticate,
      logout: logout,
      getUserPayload: getUserPayload
    };

    function authenticate(credentials) {
      var deferred = $q.defer();
      $http.post('/api/v1/authenticate', credentials).then(
        function (success) {
          $log.info(success);
          if (TokenService.saveToken(authProperties.TOKEN_STORAGE_NAME, success.data.id_token)) {
            deferred.resolve(jwtHelper.decodeToken(success.data.id_token));
          } else {
            logout();
            deferred.reject({msg: 'error on token save'});
          }
        },
        function (error) {
          $log.info(error);
          logout();
          deferred.reject(error);
        });

      return deferred.promise;
    }

    function logout() {
      TokenService.clearAll();
      $state.go('login');
    }

    function getUserPayload() {
      if (authManager.isAuthenticated()) {
        var token = TokenService.getToken(authProperties.TOKEN_STORAGE_NAME);
        return jwtHelper.decodeToken(token);
      }
      logout();
      return {};
    }
  }
})();
