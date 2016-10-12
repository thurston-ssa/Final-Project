console.log("in exercises controller");
angular 
.module("Fitness") 
.controller("exerciseController", exercise) 

exercise.$inject =['Exercises'] 

function exercise(Exercises) { 
	var ctrl = this;	
	
	Exercises.all().then(function(exercises) {
	     return ctrl.list = exercises;
})

}