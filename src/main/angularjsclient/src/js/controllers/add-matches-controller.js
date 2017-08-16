app = angular.module('ttApp');
app.controller('addMatchCtrl', ['$scope','$location' ,'matchesService', function ($scope,$location, matchesService) {

    var data = {
        "player1": "",
        "player2": "",
        "result": ""
    };

    $scope.addMatch = function(){
        data.player1 = $scope.player1;
        data.player2 = $scope.player2;
        data.result = $scope.result;

       matchesService.postNewMatch(data).then(function () {
            $location.url("/");
        });
    };



}]);