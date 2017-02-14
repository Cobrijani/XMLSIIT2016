// Include in index.html so that app level exceptions are handled.
// Exclude from testRunner.html which should run exactly what it wants to run
(function () {
  'use strict';

  angular
    .module('block.auth')
    .config(config);

  config.$inject = ['$provide'];

  function config($provide) {
    $provide.decorator('authManager', extendAuthManager);
  }

  extendAuthManager.$inject = ['$delegate', '$rootScope', '$injector', 'authOptions'];

  function extendAuthManager($delegate, $rootScope, $injector, authOptions) {

    var config = authOptions.getConfig();

    function hasOneOfTheRoles(roles) {
      var intersection = _.intersection(roles, invokeFunction(config.roleGetter, this));
      return intersection.length !== 0;
    }

    function invokeFunction(func, context) {
      if (angular.isArray(func) || angular.isFunction(func)) {
        return $injector.invoke(func, context, {});
      }
      throw new Error(func, ' must be a function');
    }

    function invokeRedirector(redirector) {
      invokeFunction(redirector, config);
    }

    function getRouteData(next) {
      if (!next) {
        return false;
      }
      /* eslint angular/no-private-call:0 */
      return (next.$$route) ? next.$$route : next.data;
    }

    function authorizeByRoles(event, next) {
      var routeData = getRouteData(next);
      if (routeData &&
        $delegate.isAuthenticated() &&
        routeData.roles &&
        angular.isArray(routeData.roles) && !hasOneOfTheRoles(routeData.roles)) {
        event.preventDefault();
        invokeRedirector(config.unauthorizedRedirector);
      }
    }

    function invokeWhenAuthenticated(event, next) {
      var routeData = getRouteData(next);
      if (routeData &&
        $delegate.isAuthenticated() &&
        routeData.requiresNonLogin &&
        routeData.requiresNonLogin === true) {

        event.preventDefault();
        invokeRedirector(config.authenticatedRedirector);
      }
    }

    function destroy(event) {
      $rootScope.$on('$destroy', event);
    }

    function redirectWhenUnauthorized() {
      var eventName = ($injector.has('$state')) ? '$stateChangeStart' : '$routeChangeStart';
      var unregister2 = $rootScope.$on(eventName, authorizeByRoles);
      destroy(unregister2);
    }

    function redirectWhenAuthenticated() {
      var eventName = ($injector.has('$state')) ? '$stateChangeStart' : '$routeChangeStart';
      var unregister1 = $rootScope.$on(eventName, invokeWhenAuthenticated);
      destroy(unregister1);
    }

    var unregister3 = $rootScope.$on('tokenHasExpired', function () {
      invokeRedirector(config.expirationRedirector);
    });
    destroy(unregister3);

    function activateAuth() {
      redirectWhenAuthenticated();
      redirectWhenUnauthorized();
      $delegate.redirectWhenUnauthenticated();
      $delegate.checkAuthOnRefresh();
    }

    return {
      authenticate: $delegate.authenticate,
      unauthenticate: $delegate.unauthenticate,
      getToken: $delegate.getToken,
      redirect: $delegate.redirect,
      checkAuthOnRefresh: $delegate.checkAuthOnRefresh,
      redirectWhenUnauthenticated: $delegate.redirectWhenUnauthenticated,
      isAuthenticated: $delegate.isAuthenticated,
      redirectWhenUnauthorized: redirectWhenUnauthorized,
      redirectWhenAuthenticated: redirectWhenAuthenticated,
      activateAuth: activateAuth
    };
  }
})();
