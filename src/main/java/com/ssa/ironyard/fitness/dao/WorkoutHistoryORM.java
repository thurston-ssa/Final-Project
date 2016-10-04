package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.WorkoutHistory;

public interface WorkoutHistoryORM extends ORM<WorkoutHistory> {
    @Override
    default String projection() {
        return table() + ".id, workout_date, w_sets, reps, weight, distance, duration";
    }

    @Override
    default String table() {
        return "history";
    }

    @Override
    default WorkoutHistory map(ResultSet results) {
        WorkoutHistory wh = new WorkoutHistory();
        Exercise e = new Exercise();
        try {
            e.setId(results.getInt("exercise_id"));
            wh.setId(results.getInt("id"));
            wh.setWorkout_date(results.getTimestamp("workout_date").toLocalDateTime());
            wh.setExercise(e);
            wh.setDistance(results.getDouble("distance"));
            wh.setWeight(results.getDouble("weight"));
            wh.setReps(results.getInt("w_sets"));
            wh.setSets(results.getInt("reps"));
            wh.setTime(Duration.ofMillis(results.getTimestamp("duration").getNanos()));
            wh.setLoaded(true);
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?,?,?,?,?,?,?,?);";
    }

    @Override
    default String prepareUpdate() {
        return "UPDATE " + table() + " SET workout_date = ?, exercise_id = ?, w_sets = ?, "
                + "reps = ?, weight =?, distance = ?, duration =? WHERE id = ?";
        }

    @Override
    default String prepareRead() {
        
        return "SELECT " + projection() + " FROM " + table() + " WHERE id=?";

    }

    @Override
    default String prepareDelete() {
       
        return "DELETE FROM " + table() + " WHERE id = ?";
 
    }
}
