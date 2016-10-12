console.log("youre in exercise factory");

angular
    .module("Fitness")
    .factory("Exercises", Exercises)

Exercises.$inject = ['$http']

function Exercises($http) {
    console.log("exercises function");
    return {
        all: function () {
            console.log("before the get request");
            return $http.get("http://localhost:8080/fitness/home/AllExercises").then(function (response) {
                return response.data;

            })
        }
    }
}