angular
  .module("store", ["ui.router"])
  .config(configure)

configure.$inject = ['$stateProvider', '$urlRouterProvider']
function configure($stateProvider, $urlRouterProvider) {
  $stateProvider
	.state("Exercises",
	{
		url: '/Exercises',
		controller: exerciseController,
		controllerAs: eC,
		templateUrl: 'fitness/Exercises.html'
		
	})
	.state("LogIn",
	{
		url: '/LogIn',
		controller: loginPageController,
		controllerAs: lPC,
		templateUrl: 'fitness/login.html'
	})
	.state("LogIn.username", //not sure about this, after user Logs In, should display /:userName
	{
	url: '/:username',
	controllerAs: aPC,
	controller: accountPageController,
	templateUrl: 'fitness/account.html'
	})

		
	.state("Logout",
	{
	url:'/LogOut',
	controller: logoutController,
	controllerAs: loC,
	templateUrl: fitness/logout.html
	})
	
	$routeProvider.otherwise('/Fitness')

	console.log("setting up", $stateProvider)

}
