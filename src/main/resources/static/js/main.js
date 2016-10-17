angular
    .module("Fitness", ["ui.router", 'ui.bootstrap.datepicker'])
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
            url: '/workouthistory',
            controller: 'workoutHistoryController',
            controllerAs: 'wHC',
            templateUrl: '/templates/workoutHistory.html'
        })
        .state('WorkoutHistory.Exercises', {
            url: '/:category',
            controller: 'whExerciseController',
            controllerAs: 'wEC',
            templateUrl: '/templates/exercises.html'
        })
        .state('History', {
            url: '/history',
            controller: 'HistoryController',
            controllerAs: 'wohC',
            templateUrl: '/templates/workoutHistoryList.html'
        })
    $urlRouterProvider.otherwise('/')

    console.log("setting up", $stateProvider)
}