(function () {
  'use strict';

  angular
    .module('block.menu')
    .provider('menuConfigurer', RouterHelperProvider);

  RouterHelperProvider.$inject = [];

  function RouterHelperProvider() {

    this.$get = MenuConfigurer;

    MenuConfigurer.$inject = [];

    function MenuConfigurer() {
      var leftNav = [];
      var rightNav = [];

      return {
        configureMenus: configureMenus,
        getMenu: getMenu
      };

      function configureMenus(menu, side) {
        menu.forEach(function (value) {
          if (side === 'right') {
            rightNav.push(value);
          } else {
            leftNav.push(value);
          }
        });
      }

      function getMenu(side) {
        if (side === 'right') {
          return rightNav;
        }
        return leftNav;
      }
    }
  }
})();
