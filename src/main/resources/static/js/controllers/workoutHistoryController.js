angular.module("Fitness").controller("workoutHistoryController", history)




history.$inject = ['$http', '$state', '$location', "Exercises", "$scope", "$stateParams"]

function history($http, $state, $location, Exercises, $scope, $stateParams) {
    var ctrl = this;

    var today = new Date();
    var mm = today.getMonth() + 1;
    var dd = today.getDate();
    var yyyy = today.getFullYear()
    today = mm + '/' + dd + '/' + yyyy;


    if ($state.params.day != undefined) {
        ctrl.date = $state.params.day;
        console.log("state params:",
            ctrl.date)

    } else {
        ctrl.date = today;
    }


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
    ctrl.exerciseId = "";
    ctrl.exerciseList = [];
    ctrl.currentExercise = "";
    ctrl.check = false;
    ctrl.exerciseDisplay = [ctrl.exerciseId, ctrl.currentExercise];

    var path = $location.absUrl();
    var length = ($location.absUrl().length) - ($location.path().length)
    var url = path.substring(35, length - 1) + "/history";
    ctrl.list = [];

    var config = {
        headers: {
            'Content-Type': undefined
        }
    }

    ctrl.type = function (id) {
        for (var i = 0; i < ctrl.arms.length; i++) {
            if (id === ctrl.arms[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.legs.length; i++) {
            if (id === ctrl.legs[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.chest.length; i++) {
            if (id === ctrl.chest[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.shoulders.length; i++) {
            if (id === ctrl.shoulders[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.core.length; i++) {
            if (id === ctrl.core[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.neck.length; i++) {
            if (id === ctrl.neck[i].id)
                ctrl.check = false;
        }
        for (var i = 0; i < ctrl.back.length; i++) {
            if (id === ctrl.back[i].id)
                ctrl.check = false;
        }

        for (var i = 0; i < ctrl.cardio.length; i++) {
            if (id === ctrl.cardio[i].id)
                ctrl.check = true;
        }
        for (var i = 0; i < ctrl.plyometrics.length; i++) {
            if (id === ctrl.plyometrics[i].id)
                ctrl.check = false;
        }
    }


    ctrl.add = function () {
        ctrl.exerciseList.push(
            new Workout(ctrl.distance, ctrl.weight, ctrl.sets, ctrl.reps, ctrl.time, ctrl.exerciseId, ctrl.currentExercise));
        console.log(ctrl.exerciseList)
        ctrl.distance = "";
        ctrl.weight = "";
        ctrl.sets = "";
        ctrl.reps = "";
        ctrl.time = "";
        ctrl.currentExercise = "";

    };

    ctrl.remove = function (evt) {
        console.log(evt.currentTarget)

        for (var i = 0; i < ctrl.exerciseList.length; i++) {
            console.log(ctrl.exerciseList[i].exerciseId)
            console.log(ctrl.exerciseList[i].exerciseId == angular.element(evt.currentTarget).data('id'))
            if (ctrl.exerciseList[i].exerciseId == angular.element(evt.currentTarget).data('id')) {
                ctrl.exerciseList.splice(i, 1);
                i = ctrl.exerciseList.length;
            }
        }
    };

    ctrl.submitForm = function (evt) {
        evt.stopPropagation();
        var _data = {
            date: ctrl.date,
            exercises: ctrl.exerciseList
        }
        $http.post(url, _data).then(function (response) {
            ctrl.exerciseList = [];
            console.log(response.data);
            return response.data
        });

    }

    function Workout(distance, weight, sets, reps, time, exerciseId, currentExercise) {
        this.exerciseId = exerciseId, this.distance = distance, this.weight = weight, this.sets = sets, this.reps = reps, this.time = time, this.exerciseId = exerciseId, this.currentExercise = currentExercise;
    };

    ctrl.addExercise = function ($event) {
        console.log($event.currentTarget)
        console.log(angular.element($event.currentTarget).data('id'))
        ctrl.exerciseId = angular.element($event.currentTarget).data('id')
        ctrl.currentExercise = angular.element($event.currentTarget).data('name')

        ctrl.type(ctrl.exerciseId);

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
                ctrl.neck.push(exercises[i]);
            } else if (exercises[i].category === "PLYOMETRICS") {
                ctrl.plyometrics.push(exercises[i]);
            } else if (exercises[i].category === "CORE") {
                ctrl.core.push(exercises[i]);
            }
        }
    })



}