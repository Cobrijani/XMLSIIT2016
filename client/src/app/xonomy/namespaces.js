/**
 * This document contains all namespaces for project
 * Created by SBratic on 3/9/2017.
 */

(function () {
  'use strict';

  angular
    .module('app')
    .constant('namespaces', {
      akt: 'http://parlament.gov.rs/rs.ac.uns.ftn.model.akt',
      meta: 'http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata',
      amadman: 'http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman',
      korisnici: 'http://parlament.gov.rs/rs.ac.uns.ftn.model.korisnici',
      document: 'xmlns:document="http://parlament.gov.rs/rs.ac.uns.ftn.model.document"'
    });
})();
