/**
 * Created by Micko on 27-Dec-16.
 */
(function () {
  'use strict';

  angular
    .module('app', [
      /* angular */
      'ngResource',
      'ngSanitize',

      /* reusable components */
      'app.core',
      'app.entities',
      'app.properties',

      /* 3rd party */
      'restangular',
      'ui.bootstrap',
      'lodash',
      'am.multiselect',
      'uuid',
      'ngTagsInput',
      'angular.filter',
      'chart.js'
    ]);
})();
