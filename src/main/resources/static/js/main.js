angular
    .module("Fitness", ["ui.router"])
    .config(configure)

configure.$inject = ['$stateProvider', '$urlRouterProvider']

function configure($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('account', {
            url: '/',
            controller: 'AccountController',
            controllerAs: 'auC',
            templateUrl: '/templates/account.html'
        })

    $urlRouterProvider.otherwise('/')

    console.log("setting up", $stateProvider)
}