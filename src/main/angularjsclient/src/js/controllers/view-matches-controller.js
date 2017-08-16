app = angular.module('ttApp');


app.controller('matchCtrl', ['$scope','$location', 'matchesService', function ($scope, $location, matchesService) {
    matchesService
        .getOverview()
        .then(function (list) {
            $scope.matches = list;
        });

    $scope.addNewMatch = function () {
       $location.url("/addMatch");
    };
}]);

