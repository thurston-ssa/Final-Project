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
                return response.data.success;
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
                console.log(response.data.success);
                return response.data.success;
=======

>>>>>>> e8282a88f96b845d0027b444b2455347845d6b2b
                return response.data;
>>>>>>> d73aadca21407cb272bfddd2c0f385f83263117c
>>>>>>> 4a324b6eff8709e50c42e79d8aafa39a2bc2416c
            })
        }
    }
}