
var app=angular.module("ttApp",["ngRoute"]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/test", {
            templateUrl: "views/test.html"
        })
        .when("/", {
            templateUrl: "views/main.html",
            controller: "matchCtrl"
        })
        .when("/addMatch", {
            templateUrl: "views/addMatch.html",
            controller: "addMatchCtrl"
        });

}]);
