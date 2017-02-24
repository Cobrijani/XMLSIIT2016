/**
 * Created by Micko on 27-Dec-16.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .config(restangularConfig);

  restangularConfig.$inject = ['RestangularProvider'];

  /* @ngInject */
  function restangularConfig(RestangularProvider) {
    RestangularProvider.setBaseUrl("api/v1");
    RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'})
  }
})();
