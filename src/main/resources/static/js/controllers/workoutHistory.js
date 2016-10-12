angular.module("Fitness").controller("workoutHistoryController", history)

history.$inject = ['$http', '$state']

function history($http, $state) {
    var ctrl = this;

    console.log('State is', $state)
    console.log('id is', $state.params.id)
        //return $http.get("http://localhost:8080/fitness/home/)
}