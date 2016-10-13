angular.module("Fitness").controller("workoutHistoryController", history)

history.$inject = ['$http', '$state', '$location']

function history($http, $state, $location) {
    var ctrl = this;
    var path = $location.absUrl();
    var length = ($location.absUrl().length) - ($location.path().length)
    ctrl.list = [];
    console.log(length)
    var url = path.substring(35, length - 1) + "/history";

    var data = $.param({
        id: url,
        workout_date: ctrl.date,
        weight: ctrl.weight,
        height: ctrl.height,
        reps: ctrl.reps,
        sets: ctrl.sets,
        distance: ctrl.distance,
        duration: ctrl.time
    });

    var config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
        }
    }


    ctrl.submitForm = function () {
        ctrl.list.push(data);
        $http.post(url, data, config).then(function (response) {
            console.log(response);
        });

    }
}