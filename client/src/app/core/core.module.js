(function () {
  'use strict';

  angular
    .module('app.core', [
      'block.auth',
      'block.router',
      'block.exception',
      'block.common',
      'block.localStorage',
      'block.util',
      'block.menu',

      'angular-bind-html-compile'
    ]);
})();
