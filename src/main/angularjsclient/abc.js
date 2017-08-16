var liste = [{
    "player1": "Niklas",
    "player2": "Stefan",
    "result": "1:3",
    "_links": {
        "self": {
            "href": "http://localhost:8080/game/1"
        },
        "match": {
            "href": "http://localhost:8080/game/1"
        }
    }
},
    {
        "player1": "Rene",
        "player2": "Bernhard",
        "result": "2:3",
        "_links": {
            "self": {
                "href": "http://localhost:8080/game/2"
            },
            "match": {
                "href": "http://localhost:8080/game/2"
            }
        }
    },
    {
        "player1": "Sven",
        "player2": "Niklas",
        "result": "3:1",
        "_links": {
            "self": {
                "href": "http://localhost:8080/game/3"
            },
            "match": {
                "href": "http://localhost:8080/game/3"
            }
        }
    },
    {
        "player1": "Sven",
        "player2": "Niklas",
        "result": "2:3",
        "_links": {
            "self": {
                "href": "http://localhost:8080/game/4"
            },
            "match": {
                "href": "http://localhost:8080/game/4"
            }
        }
    },
    {
        "player1": "Sven",
        "player2": "Thomas",
        "result": "3:2",
        "_links": {
            "self": {
                "href": "http://localhost:8080/game/5"
            },
            "match": {
                "href": "http://localhost:8080/game/5"
            }
        }
    }],
    filteredList = [];

    liste.forEach(function (item) {
       //console.log(item);
       delete item['_links'];
       filteredList.push(item);
    });



console.log(JSON.stringify(filteredList,null,'\t'));