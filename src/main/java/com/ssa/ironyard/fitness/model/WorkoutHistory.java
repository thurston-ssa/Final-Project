package com.ssa.ironyard.fitness.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkoutHistory implements DomainObject{ 
Integer id;

LocalDateTime workout_date;
Exercise exercise;
int sets;
int reps;
float weight;
Duration time;

public WorkoutHistory() {
    // TODO Auto-generated constructor stub
}


public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public LocalDateTime getWorkout_date() {
    return workout_date;
}

public void setWorkout_date(LocalDateTime workout_date) {
    this.workout_date = workout_date;
}

public Exercise getExercise() {
    return exercise;
}

public void setExercise(Exercise exercise) {
    this.exercise = exercise;
}

public int getReps() {
    return reps;
}

public void setReps(int reps) {
    this.reps = reps;
}

public float getWeight() {
    return weight;
}

public void setWeight(float weight) {
    this.weight = weight;
}

@Override
public String toString() {
    return "WorkoutHistory [id=" + id + ", workout_date=" + workout_date + ", exercise=" + exercise + ", reps=" + reps
            + ", weight=" + weight + "]";
}

@Override
public WorkoutHistory clone() {

    try{
        WorkoutHistory wh = (WorkoutHistory) super.clone();
        wh.setExercise(this.exercise.clone());
        return wh;
    }
    catch(Exception e){
        
    }
    return null;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());

    return result;
}

@Override
public boolean deeplyEquals(DomainObject obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    WorkoutHistory other = (WorkoutHistory) obj;
    if (exercise == null) {
        if (other.exercise != null)
            return false;
    } else if (!exercise.equals(other.exercise))
        return false;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    if (reps != other.reps)
        return false;
    if (sets != other.sets)
        return false;
    if (time == null) {
        if (other.time != null)
            return false;
    } else if (!time.equals(other.time))
        return false;
    if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
        return false;
    if (workout_date == null) {
        if (other.workout_date != null)
            return false;
    } else if (!workout_date.equals(other.workout_date))
        return false;
    return true;
}



}
