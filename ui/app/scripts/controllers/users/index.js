angular.module('uiApp').controller('UserIndexCtrl', ['$scope', '$http', 'User', function ($scope, $http, User) {
  'use strict';

  $scope.users = User.query();

  $scope.add = function () {
    $http({method: "GET", url: "/api/user/add"}).
      success(function(data, status) {
        console.log("追加っす！");
        $scope.users = User.query();
      }).
      error(function(data, status) {
        alert("失敗＞＜");
      });
  };

}]);
