package com.ssa.ironyard.fitness.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkoutHistory implements DomainObject {
    Integer id;

    LocalDateTime workout_date;
    Exercise exercise;
    int sets;
    int reps;
    double weight;
    int time;
    Account account;
    double distance;
    boolean isLoaded = false;

    public WorkoutHistory() {
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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        this.isLoaded = loaded;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public WorkoutHistory clone() {

        try {
            WorkoutHistory wh = (WorkoutHistory) super.clone();
            wh.setExercise(this.exercise.clone());
            wh.setAccount(this.account.clone());
            return wh;
        } catch (Exception e) {

        }
        return null;
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
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
            return false;
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
        if (isLoaded != other.isLoaded)
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
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        if (workout_date == null) {
            if (other.workout_date != null)
                return false;
        } else if (!workout_date.equals(other.workout_date))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        WorkoutHistory other = (WorkoutHistory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WorkoutHistory [id=" + id + ", workout_date=" + workout_date + ", exercise=" + exercise + ", sets="
                + sets + ", reps=" + reps + ", weight=" + weight + ", time=" + time + ", account=" + account
                + ", distance=" + distance + ", isLoaded=" + isLoaded + "]";
    }

}
