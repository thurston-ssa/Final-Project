angular.module("Fitness").controller("workoutHistoryController", history)

history.$inject = ['$http', '$state', '$location']

function history($http, $state, $location) {
    var ctrl = this;
    ctrl.distance = "";
    ctrl.weight = "";
    ctrl.sets = "";
    ctrl.reps = "";
    ctrl.time = "";
    ctrl.date = "";
    var path = $location.absUrl();
    var length = ($location.absUrl().length) - ($location.path().length)
    ctrl.list = [];
    console.log(length)
    var url = path.substring(35, length - 1) + "/history";


    var config = {
        headers: {
            'Content-Type': undefined
        }
    }

    ctrl.add = function () {
        ctrl.list.push(
            new Workout(ctrl.distance, ctrl.weight, ctrl.sets, ctrl.reps, ctrl.time)
        )
    }

    ctrl.submitForm = function () {

        var _data = {
            date: ctrl.date,
            exercises: ctrl.list
        }
        $http.post(url, _data).then(function (response) {
            console.log(response.data);
            return response.data
        });

    }

    function Workout(distance, weight, sets, reps, time, exerciseId) {
        this.distance = distance, this.weight = weight, this.sets = sets, this.reps = reps, this.time = time, this.exerciseId = exerciseId;
    }
    
   
    

}