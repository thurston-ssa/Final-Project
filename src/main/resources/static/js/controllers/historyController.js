angular
    .module("Fitness")
    .controller("HistoryController", history)

history.$inject = ['$state', '$http', '$location']

function history($state, $http, $location) {
    var ctrl = this;
    var path = $location.absUrl();
    var length = ($location.absUrl().length) - ($location.path().length)
    ctrl.list = [];
    console.log(length)
    var url = path.substring(0, length - 1) + "/history";

    return $http.get(url).then(function (response) {
        console.log(response.data);
    })
}