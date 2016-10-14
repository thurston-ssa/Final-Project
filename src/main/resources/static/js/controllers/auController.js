console.log("found account controller")
angular
    .module("Fitness")
    .controller("AccountController", accountpage)

accountpage.$inject = ['$state', '$http']

function accountpage($state, $http) {
    var ctrl = this;
    ctrl.button = bttn;
    ctrl.retrieveUsername = $state.params.id;
    return {
    	linkswitcher : function (bttn){
    		console.log(bttn.toString);
    	}
    	
    	
    }
     



}