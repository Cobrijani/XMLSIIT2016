(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('FileFactory', FileFactory);

  FileFactory.$inject = ['$http', '$window'];

  function FileFactory($http, $window) {
    //content

    return {
      getDocumentAsArrayBuffer: getDocumentAsArrayBuffer,
      openFileInNewWindow: openFileInNewWindow,
      getDocument: getDocument
    };

    //////////////////////////////

    function getDocumentAsArrayBuffer(entityName, id, format) {
      return $http.get('/api/v1/' + entityName + '/' + id, {responseType: 'arraybuffer', headers: {'Accept': format}});
    }

    function getDocument(entityName, id, format) {
      return $http.get('/api/v1/' + entityName + '/' + id, {headers: {'Accept': format}});
    }


    function openFileInNewWindow(data, t) {
      $window.open(URL.createObjectURL(new Blob([data], {type: t})));
    }

  }
})();
