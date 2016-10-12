angular
    .module("Fitness", ["ui.router"])
    .config(configure)

configure.$inject = ['$stateProvider', '$urlRouterProvider']

function configure($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('default', {
            url: '/',
            controller: 'AccountController',
            controllerAs: 'auC',
            templateUrl: '/templates/account.html'
        })
        .state('Exercises', {
            url: '/exercises',
            controller: 'exerciseController',
            controllerAs: 'eC',
            templateUrl: '/templates/exercises.html'
        })
        .state('WorkoutHistory', {
            url: '/exercises',
            controller: 'workoutHistoryController',
            controllerAs: 'wHC',
            templateUrl: '/templates/workoutHistory.html'
        })
    $urlRouterProvider.otherwise('/')

    console.log("setting up", $stateProvider)
}