console.log("hello");
angular 
.module("Fitness") 
.controller("loginController", login) 

login.$inject =['$state', '$http'] 

function login($state, $http) { 
	var ctrl = this;	

//	return $http.get("http://localhost:8080/fitness/" + ctrl.username).then(function(res) {
//	return res.data })
	
	ctrl.username="Foo";
	console.log(ctrl.username);

}


//var ctrl = this
//account.all.then(function(accountinfo){
//accountinfo.forEach(function(obj) {
//if (obj.id === Number($state.params.id)) {
//ctrl.account = obj
//} 	 
//}

//,
//save: function(data) {
//console.log('should save', data)
//return Promise.resolve({
//id: 5
//})
//}

////Mock data
//return Promise.resolve([
//{ id: 1, name: "Foo" },
//{ id: 5, name: "Bar" }
//])
