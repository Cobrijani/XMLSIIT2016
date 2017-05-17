/**
 * Created by SBratic on 3/19/2017.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .constant('roles', {
      odbornik: 'odbornik',
      predsednik: 'predsednik',
      gradjanin: 'gradjanin'
    });
})();
