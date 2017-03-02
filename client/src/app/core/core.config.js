(function () {
  'use strict';

  angular
    .module('app.core')
    .config(CoreConfiguration)
    .constant('toastr', toastr);

  CoreConfiguration.$inject = ['toastr'];

  function CoreConfiguration(toastr) {
    //content
    toastr.options.timeOut = 4000;
    toastr.options.positionClass = 'toast-bottom-left';
  }
})();
