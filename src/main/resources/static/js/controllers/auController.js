console.log("found account controller")
angular
    .module("Fitness")
    .controller("AccountController", accountpage)

accountpage.$inject = ['$state', '$http']

function accountpage($state, $http) {
    var ctrl = this;
    ctrl.username = "fitness123"
        //	return $http.get("http://localhost:8080/fitness/" + ctrl.username).then(function (res) {
        //	    return res.data
        //	})



}