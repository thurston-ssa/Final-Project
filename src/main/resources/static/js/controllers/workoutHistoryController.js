angular.module("Fitness").controller("workoutHistoryController", history)

history.$inject = ['$http', '$state', '$location', "Exercises", "$scope"]

function history($http, $state, $location, Exercises, $scope) {
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
            new Workout(ctrl.distance, ctrl.weight, ctrl.sets, ctrl.reps, ctrl.time, ctrl.exerciseId))
        console.log(ctrl.exerciseList[0].exercise_name)
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

    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.inlineOptions = {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
    };

    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };

    // Disable weekend selection
    function disabled(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    $scope.toggleMin = function () {
        $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
        $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };

    $scope.toggleMin();

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };

    $scope.setDate = function (year, month, day) {
        $scope.dt = new Date(year, month, day);
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    $scope.popup1 = {
        opened: false
    };

    $scope.popup2 = {
        opened: false
    };

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    $scope.events = [
        {
            date: tomorrow,
            status: 'full'
    },
        {
            date: afterTomorrow,
            status: 'partially'
    }
  ];

    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    }

}