/**
 * Created by SBratic on 3/13/2017.
 */

(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('ServerUtils', ServerUtils);

  ServerUtils.$inject = ['$http'];

  function ServerUtils($http) {
    return {
      validate: validate
    };

    function validate(content, schemaName) {
      return $http.post('/api/v1/util/validate?xsd=' + schemaName, content);
    }
  }
})();
