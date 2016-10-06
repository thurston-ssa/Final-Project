console.log("hello");
angular 
.module("Fitness") 
.controller("loginController", login) 

login.$inject =['$state', '$http'] 

function login($state, $http) { 
	var ctrl = this;
	ctrl.navbar = true;
	return {
		submitter:function(){
			 ctrl = this;
			$http.post("http://localhost:8080/fitness/" + ctrl.username +"/"+ ctrl.password).then(function(res) {
				if(res.data.success){
					console.log("success");
					console.log(res.data);
					$state.go("AccountPage", {username: ctrl.username});
					return ctrl.navbar;
				}
				else{
					console.log("youre stupid");
					$state.go("login");
					//console.log("didn't work!");
				}
			})
		}
	}
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
