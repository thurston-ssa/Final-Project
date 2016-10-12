console.log("in exercises controller");
angular
    .module("Fitness")
    .controller("exerciseController", exercise)

exercise.$inject = ['Exercises']

function exercise(Exercises) {
    console.log("here");
    var ctrl = this;

    Exercises.all().then(function (exercises) {
<<<<<<< HEAD
        return ctrl.list = exercises;
=======
        console.log(exercises);
        ctrl.list = exercises;
>>>>>>> 4a324b6eff8709e50c42e79d8aafa39a2bc2416c
    })

}