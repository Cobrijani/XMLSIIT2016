/**
 * Created by SBratic on 2/24/2017.
 */


(function () {
  'use strict';

  angular
    .module('app.entities')
    .factory('GenericResource', GenericResource);

  GenericResource.$inject = ['Restangular'];

  function GenericResource(Restangular) {

    return {
      getEntities: getEntities,
      getEntityByIdentifier: getEntityByIdentifier,
      postEntity: postEntity,
      putEntity: putEntity
    };


    function getEntities(entityName, params) {
      return Restangular.all(entityName).customGET('', params);
    }

    function getEntityByIdentifier(entityName, identifier, headers) {
      return Restangular.one(entityName, identifier).get('', headers);
    }

    function postEntity(entityName, requestBody, headers) {
      return Restangular.all(entityName).post(requestBody, '', headers);
    }

    function putEntity(entityName, requestBody, headers) {
      return Restangular.all(entityName).customPUT(requestBody, '', headers);
    }
  }
})();
