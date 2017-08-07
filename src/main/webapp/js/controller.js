var app = angular.module('myApp', []);

app.controller('persCtrl', function($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";

    $scope.fullName = function() {
        return $scope.firstName + " " + $scope.lastName;
    };
});