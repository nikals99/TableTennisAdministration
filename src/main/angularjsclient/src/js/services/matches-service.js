angular
    .module('ttApp')
    .factory('matchesService', ['$http','$q', function ($http, $q) {

        var service = {
            getAll: getAll,
            getOverview: getOverview,
            postNewMatch: postNewMatch
        };

        var list;


        /**
         *
         * @param data
         */
        function postNewMatch(data) {
            return $http
                .post("/game", data)
                .then(function (response) {
                    list.unshift(response.data);
                    return true;
                });
        }

        /**
         *
         * @return {Promise}
         */
        function getAll() {
            if (list != null) {
                return $q(function (resolve) {
                    resolve(list);
                });

            } else {
                return $http
                    .get("/game")
                    .then(function (response) {
                        if (response.data._embedded.matches instanceof Array) {
                            list = response.data._embedded.matches;
                            return _reverseList(response.data._embedded.matches);
                        }
                    });
            }
        }

        /**
         *
         * @returns {Array}
         */
        function getOverview() {
            return getAll()
                .then(function (list) {

                    return list.map(function (item) {
                        return {player1: item.player1, player2: item.player2, result: item.result};
                    });
            });
        }


        /**
         *
         * @method reverseList
         * @param {Array} liste
         * @return {Array}
         * @private
         */
        function _reverseList(liste) {
            return liste.reverse();

        }


        return service;

    }]);

