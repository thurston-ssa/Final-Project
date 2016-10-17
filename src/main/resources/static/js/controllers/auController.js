console.log("found account controller")
angular.module("Fitness").controller("AccountController", accountpage)

accountpage.$inject = [ '$state', '$http', '$location', '$scope' ]

function accountpage($state, $http, $location, $scope) {
	var path = $location.absUrl();
	var length = ($location.absUrl().length) - ($location.path().length);
	console.log(length);
	var url = path.substring(35, length - 1);
	console.log(url);

	$scope.regimen = [  ]
	
	$scope.createRegimen = function(){
		$state.go('CreateRegimen');
	}

	return $http.get("http://localhost:8080/fitness/home/" + url).then(function(res) {
		console.log("http://localhost:8080/fitness/home/" + url)
		console.log(res.data);
		$scope.username = res.data.success.username;
		
		return $http.get("http://localhost:8080/fitness/home/" + url + "/regimen").then(function(res) {
			console.log("http://localhost:8080/fitness/home/" + url + "/regimen")
			console.log(res.data.success);
			
			 for (i = 0; i < res.data.success.length; i++) {
				 $scope.regimen.push(res.data.success[i])
			 }
			
		})
	})

}