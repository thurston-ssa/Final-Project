angular
.module("Fitness", ["ui.router"])
.config(configure)

configure.$inject = ['$stateProvider', '$urlRouterProvider']
function configure($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state("Exercises",
			{
		url: '/Exercises',
		controller: 'exerciseController',
		templateUrl: 'exercises.html'

			})
			.state("login",
			{
			url: '/login',
			controller: 'loginController',
			controllerAs: 'lC',
			templateUrl: 'login.html'
			})
			
//			.state("LogIn.username", //not sure about this, after user Logs In, should display /:userName
//			{
//			url: '/:username',
//			controllerAs: aaPC,
//			controller: aaccountPageController,
//			templateUrl: 'fitness/account.html'
//			})


			.state("Logout",
			{
			url:'/LogOut',
			controller: logoutController,
			controllerAs: loC,
			templateUrl: fitness/logout.html
			})

			.state("AboutUs",
			{
			url:'/AboutUs',
			controller: auController,
			controllerAs: auC,
			templateUrl: fitness/aboutus.html
			})
			
			$urlRouterProvider.otherwise('/')

			console.log("setting up", $stateProvider)

}
