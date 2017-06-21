(function () {
  'use strict';

  angular
    .module('app')
    .controller('DeleteAktModalController', DeleteAktModalController);

  DeleteAktModalController.$inject = ['$uibModalInstance', 'akt'];

  function DeleteAktModalController($uibModalInstance, akt) {
    var vm = this;

    vm.close = close;
    vm.acceptRevoke = acceptRevoke;

    activate();

    ////////////////

    function activate() {}

    function close() {
      $uibModalInstance.dismiss('cancel');
    }

    function acceptRevoke() {
      $uibModalInstance.close(akt);
    }

  }
})();
