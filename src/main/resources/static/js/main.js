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
            url: '/workouthistory',
            controller: 'workoutHistoryController',
            controllerAs: 'wHC',
            templateUrl: '/templates/workoutHistory.html',
            params: {
                "target": null
            }
        })
        .state('WorkoutHistory.Exercises', {
            url: '/:category',
            controller: 'whExerciseController',
            controllerAs: 'wEC',
            templateUrl: '/templates/exercises.html'
        })
        .state('History', {
            url: '/history/{month}',
            controller: 'monthlyWorkoutController',
            templateUrl: '/templates/calendar.html'
        })
    $urlRouterProvider.otherwise('/')

    console.log("setting up", $stateProvider)
}