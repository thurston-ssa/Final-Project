package com.ssa.ironyard.fitness.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkoutLogThingy
{
    
    LocalDateTime workout_date;
    Exercise exercise;
    int sets;
    int reps;
    double weight;
    Duration time;
    Account account;
    double distance;
    public LocalDateTime getWorkout_date()
    {
        return workout_date;
    }
    public void setWorkout_date(LocalDateTime workout_date)
    {
        this.workout_date = workout_date;
    }
    public Exercise getExercise()
    {
        return exercise;
    }
    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }
    public int getSets()
    {
        return sets;
    }
    public void setSets(int sets)
    {
        this.sets = sets;
    }
    public int getReps()
    {
        return reps;
    }
    public void setReps(int reps)
    {
        this.reps = reps;
    }
    public double getWeight()
    {
        return weight;
    }
    public void setWeight(double weight)
    {
        this.weight = weight;
    }
    public Duration getTime()
    {
        return time;
    }
    public void setTime(Duration time)
    {
        this.time = time;
    }
    public Account getAccount()
    {
        return account;
    }
    public void setAccount(Account account)
    {
        this.account = account;
    }
    public double getDistance()
    {
        return distance;
    }
    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    

}
