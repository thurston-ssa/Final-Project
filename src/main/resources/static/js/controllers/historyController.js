angular
    .module("Fitness")
    .controller("HistoryController", history)

history.$inject = ['$state', '$http', '$location']

function history($state, $http, $location) {
    var ctrl = this;
    var path = $location.absUrl();
    var url = path.substring(0, path.length - 9);
    console.log(url);
    return $http.get(url + "/history").then(function (response) {
        console.log(response.data);
    })
}