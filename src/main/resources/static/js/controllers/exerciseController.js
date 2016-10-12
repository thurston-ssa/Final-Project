console.log("in exercises controller");
angular 
.module("Fitness") 
.controller("exerciseController", exercise) 

exercise.$inject =['Exercises'] 

function exercise(Exercises) { 
	console.log("here");
	var ctrl = this;	
	
	Exercises.all().then(function(exercises) {
		console.log(exercises);
	    return ctrl.list = exercises;
})

}