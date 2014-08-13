'use strict';

angular.module('uiApp', ['ngResource']);
angular.element(document).ready(function () {
    'use strict';
    angular.bootstrap(document, ['uiApp']);
});

angular.module('uiApp', ['ngResource'])
  .config(function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    console.log("hoge");
    $routeProvider
      .when('/', { templateUrl: '/views/main.html', controller: 'MainCtrl' })
      .when('/users', { templateUrl: '/views/users/index.html', controller: 'UserIndexCtrl' })
      .when('/user/:userId', { templateUrl: '/views/users/edit.html', controller: 'UserEditCtrl' })
      .otherwise({ redirectTo: '/' });
  });

