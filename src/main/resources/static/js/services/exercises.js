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
<<<<<<< HEAD
                console.log(response.data.success);
                return response.data.success;
=======

                return response.data;
>>>>>>> d73aadca21407cb272bfddd2c0f385f83263117c
            })
        }
    }
}