angular 
.module("Fitness") 
.factory("Accounts", Accounts) 

//FOR ADMIN

Accounts.$inject =['$http'] 
function Accounts($http) { 
	 return {
		all: function(){
			return $http.get("http://localhost:8080/fitness/accounts").then(function(res) {
			return res.data })
		} 	 
	 }
}
 
