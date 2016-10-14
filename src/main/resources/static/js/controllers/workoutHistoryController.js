angular.module("Fitness").controller("workoutHistoryController", history)

history.$inject = ['$http', '$state', '$location', "Exercises"]

function history($http, $state, $location, Exercises) {
    var ctrl = this;

    ctrl.arms = [];
    ctrl.legs = [];
    ctrl.cardio = [];
    ctrl.chest = [];
    ctrl.shoulders = [];
    ctrl.back = [];
    ctrl.neck = [];
    ctrl.plyometrics = [];
    ctrl.core = [];
    ctrl.distance = "";
    ctrl.weight = "";
    ctrl.sets = "";
    ctrl.reps = "";
    ctrl.time = "";
    ctrl.date = "10/14/2016";
    ctrl.exerciseId = "";
    ctrl.exerciseList = [];
    ctrl.currentExercise;

    var path = $location.absUrl();
    var length = ($location.absUrl().length) - ($location.path().length)
    var url = path.substring(35, length - 1) + "/history";
    ctrl.list = [];

    var config = {
        headers: {
            'Content-Type': undefined
        }
    }

    ctrl.add = function () {
        ctrl.exerciseList.push(
            new Workout(ctrl.distance, ctrl.weight, ctrl.sets, ctrl.reps, ctrl.time, ctrl.exerciseId)
        )
    }

    ctrl.submitForm = function (evt) {
        evt.stopPropagation();
        var _data = {
            date: ctrl.date,
            exercises: ctrl.exerciseList
        }
        $http.post(url, _data).then(function (response) {
            console.log(response.data);
            return response.data
        });

    }

    function Workout(distance, weight, sets, reps, time, exerciseId) {
        this.exerciseId = exerciseId, this.distance = distance, this.weight = weight, this.sets = sets, this.reps = reps, this.time = time, this.exerciseId = exerciseId;
    };

    ctrl.addExercise = function ($event) {
        console.log($event.currentTarget)
        console.log(angular.element($event.currentTarget).data('id'))
        ctrl.exerciseId = angular.element($event.currentTarget).data('id')

    };

    Exercises.all().then(function (exercises) {
        ctrl.list = exercises;
        for (i = 0; i < exercises.length; i++) {
            if (exercises[i].category === "ARMS") {
                ctrl.arms.push(exercises[i]);
            } else if (exercises[i].category === "CARDIO") {
                ctrl.cardio.push(exercises[i]);
            } else if (exercises[i].category === "LEGS") {
                ctrl.legs.push(exercises[i]);
            } else if (exercises[i].category === "CHEST") {
                ctrl.chest.push(exercises[i]);
            } else if (exercises[i].category === "SHOULDERS") {
                ctrl.shoulders.push(exercises[i]);
            } else if (exercises[i].category === "BACK") {
                ctrl.back.push(exercises[i]);
            } else if (exercises[i].category === "NECK") {
                console.log("necks found")
                ctrl.neck.push(exercises[i]);
            } else if (exercises[i].category === "PLYOMETRICS") {
                ctrl.plyometrics.push(exercises[i]);
            } else if (exercises[i].category === "CORE") {
                ctrl.core.push(exercises[i]);
            }
        }
    })




}