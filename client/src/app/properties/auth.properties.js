(function () {
  'use strict';

  angular
    .module('app.properties')
    .constant('authProperties', {
      TOKEN_HEADER_NAME: 'authorization',
      TOKEN_STORAGE_NAME: 'token',
      STORAGE_PREFIX_NAME: 'nwt-error-tracker'
    });

})();
