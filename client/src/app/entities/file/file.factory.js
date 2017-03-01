(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('FileFactory', FileFactory);

  FileFactory.$inject = ['$http', '$window'];

  function FileFactory($http, $window) {
    //content

    return {
      getDocumentInFormat: getDocumentInFormat
    };

    //////////////////////////////

    function getDocumentInFormat(entityName, id, format) {
      $http.get('/api/v1/' + entityName + '/' + id, {responseType: 'arraybuffer', headers: {'Accept': format}})
        .then(function (success) {
          openFile(success.data, {type: format});
        });
    }

    function openFile(data, type) {
      $window.open(URL.createObjectURL(new Blob([data], type)));
    }

  }
})();
