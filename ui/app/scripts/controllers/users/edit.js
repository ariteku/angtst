angular.module('uiApp').controller('UserEditCtrl', ['$scope', '$routeParams', '$location', 'User', function ($scope, $routeParams, $location, User) {
  'use strict';

  $scope.user = User.get({userId: $routeParams.userId});

  $scope.save = function (user) {
    user.$save({userId: user.id}, function (user) {
      console.log('save', user);
    });
  };

  $scope.remove = function (user) {
    user.$remove({userId: user.id}, function () {
      console.log('remove', user);
      $location.path('/users');
    });
  };

}]);
