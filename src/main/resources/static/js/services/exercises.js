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


                    return response.data;
                }
            }
        }
=======
                return response.data;

            })
        }
    }
}
>>>>>>> 127d8dff5b1183662603f4683c45c5eb79617ea8
