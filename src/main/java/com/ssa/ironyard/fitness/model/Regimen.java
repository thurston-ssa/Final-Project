package com.ssa.ironyard.fitness.model;

import java.time.Duration;

public class Regimen implements DomainObject {
    Integer id;
    DAY day;
    Exercise exercise;
    int sets;
    int reps;
    double weight;
    Duration time;
    Account account;
    double distance;
    boolean isLoaded = false;

    public Regimen() {
    }

    public enum DAY {
        Monday("Mon"), Tuesday("Tues"), Wednesday("Wed"), Thursday("Thurs"), Friday("Friday"), Saturday("Sat"), Sunday(
                "Sun");

        public final String abbrev;

        private DAY(String abbrev) {
            this.abbrev = abbrev;
        }

        public static DAY getInstance(String abbrev) {
            for (DAY d : values()) {
                if (d.abbrev.equals(abbrev))
                    return d;
            }
            return null;
        }
    }

    public DAY getDay() {
        return day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDay(DAY day) {
        this.day = day;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public void setLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
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
        Regimen other = (Regimen) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        return true;
    }

    @Override
    public boolean deeplyEquals(DomainObject obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Regimen other = (Regimen) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (day != other.day)
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
        return true;
    }

    @Override
    public String toString() {
        return "Regimen [id=" + id + ", day=" + day + ", exercise=" + exercise + ", sets=" + sets + ", reps=" + reps
                + ", weight=" + weight + ", time=" + time + ", account=" + account + ", distance=" + distance
                + ", isLoaded=" + isLoaded + "]";
    }
    
    @Override
    public Regimen clone() {
        try {
            return (Regimen) super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return null;
    }
}
