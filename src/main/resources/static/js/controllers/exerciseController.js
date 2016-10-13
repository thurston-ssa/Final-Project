console.log("in exercises controller");
angular
    .module("Fitness")
    .controller("exerciseController", exercise)

exercise.$inject = ['Exercises']

function exercise(Exercises) {
    console.log("here");
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
    Exercises.all().then(function (exercises) {

        console.log(exercises);
        ctrl.list = exercises;
        for(i = 0; i < exercises.length; i++ ){
        	console.log(exercises[i].category)
        	console.log(exercises[i]);
        	if(exercises[i].category === "Arms"){
        		ctrl.arms.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Cardio"){
        		ctrl.cardio.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Legs"){
        		ctrl.legs.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Chest"){
        		ctrl.chest.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Shoulders"){
        		ctrl.shoulders.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Back"){
        		ctrl.back.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Neck"){
        		ctrl.neck.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Plyometrics"){
        		ctrl.plyometrics.push(exercises[i]);
        	}
        	else if(exercises[i].category === "Core"){
        		ctrl.core.push(exercises[i]);
        	} 	
        }
    })
    
}