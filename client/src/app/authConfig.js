(function () {
  'use strict';

  angular
    .module('app')
    .config(AuthConfiguration);

  AuthConfiguration.$inject = ['$httpProvider', 'jwtOptionsProvider', 'authOptionsProvider', 'authProperties'];

  function AuthConfiguration($httpProvider, jwtOptionsProvider, authOptionsProvider, authProperties) {
    //content
    /* eslint angular/module-getter:0 */
    jwtOptionsProvider.config({
      tokenGetter: ['TokenService', function (TokenService) {
        return TokenService.getToken(authProperties.TOKEN_STORAGE_NAME);
      }],
      authPrefix: '',
      authHeader: authProperties.TOKEN_HEADER_NAME,
      unauthenticatedRedirector: ['$state', 'UserJwtResource', function ($state, UserJwtResource) {
        UserJwtResource.logout();
      }]
    });
    /* eslint angular/module-getter:0 */
    authOptionsProvider.config({
      authenticatedRedirector: ['$state', function ($state) {
        $state.go('main');
      }],
      unauthorizedRedirector: ['$state', function ($state) {
        $state.go('unauthorized');
      }],
      roleGetter: [function () {
      }],
      expirationRedirector: ['UserJwtResource', function (UserJwtResource) {
        UserJwtResource.logout();
      }]
    });

    $httpProvider.interceptors.push('jwtInterceptor');
  }
})();
