console.log("found account controller")
angular
    .module("Fitness")
    .controller("AccountController", accountpage)

accountpage.$inject = ['$state', '$http', '$location']

function accountpage($state, $http, $location) {
    var ctrl = this;
    var length = ($location.absUrl().length) - ($location.path().length);
    var url = path.substring(35, length-1);
    console.log(url);
    $http.get("http://localhost:8080/fitness/home/" + url ).then(function(res) {
    		console.log(res.data.success);
	    })
    
}