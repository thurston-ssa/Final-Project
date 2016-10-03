angular
.module("Fitness", ["ui.router"])
.config(configure)

configure.$inject = ['$stateProvider', '$urlRouterProvider']
function configure($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	.state('AccountPage', {
		url: '/:username',
		controller: 'AccountPageController',
		controllerAs: 'AP',
		templateUrl: 'fitness/account.html'
	})

	 .state('LogIn', {
      url: '/LogIn',
      controller: 'LogInController',
      controllerAs: 'LI',
      templateUrl: 'fitness/login.html'
    })
    
	$urlRouterProvider.otherwise('/Fitness')

	console.log("setting up", $stateProvider)
	
}
