angular 
.module("Fitness") 
.controller("auController", aboutUs) 

aboutUs.$inject =['$state', '$http'] 

function aboutUs($state, $http) { 
	var ctrl = this;	

//	return $http.get("http://localhost:8080/fitness/" + ctrl.username).then(function(res) {
//	return res.data })
	
	ctrl.username="Foo";
	console.log(ctrl.username);

}
