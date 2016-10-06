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
				url: '/',
				controller: 'loginController',
				controllerAs: 'lC',
			
					})

					.state("AccountPage",
					{
					url:'/:username',
					controller: 'auController',
					controllerAs: 'auC',
					templateUrl: 'account.html'
					})

					.state("AboutUs",
							{
						url:'/AboutUs',
						controller: 'auController',
						templateUrl: 'aboutus.html'
							})

							$urlRouterProvider.otherwise('/')

							console.log("setting up", $stateProvider)

}
