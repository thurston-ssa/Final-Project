angular 
.module("Fitness") 
.controller("exerciseController", exercise) 

exercise.$inject =['$state', '$http'] 

function exercise($state, $http) { 
	var ctrl = this;	

return $http.get("http://localhost:8080/fitness/exercises").then(function(res) {

})

}