(function () {
  'use strict';

  angular
    .module('block.auth')
    .provider('authOptions', authOptionsProvider);

  authOptionsProvider.$inject = [];

  function authOptionsProvider() {

    var globalConfig = {};

    this.config = function (value) {
      globalConfig = value;
    };

    this.$get = authOptions;

    authOptions.$inject = [];

    function authOptions() {
      var options = {
        authenticatedRedirectPath: "/",
        authenticatedRedirector: ['$location', function ($location) {
          $location.path(this.authenticatedRedirectPath);
        }],
        unauthorizedRedirectPath: '/',
        unauthorizedRedirector: ['$location', function ($location) {
          $location.path(this.unauthorizedRedirectPath);
        }],
        expirationRedirectPath: '/',
        expirationRedirector: ['$location', function ($location) {
          $location.path(this.expirationRedirectPath);
        }],
        roleGetter: function () {
          return null;
        }
      };

      function AuthOptions() {
        /* eslint no-unused-vars:0 */
        var config = this.config = angular.extend({}, options, globalConfig);
      }

      AuthOptions.prototype.getConfig = function () {
        return this.config;
      };

      return new AuthOptions();
    }
  }
})();
