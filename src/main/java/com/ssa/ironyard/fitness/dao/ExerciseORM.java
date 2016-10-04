package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssa.ironyard.fitness.model.Exercise;

public interface ExerciseORM extends ORM<Exercise> {
    @Override
    default String projection() {
        return table() + ".id, exercise_name, equipment, region";
    }

    @Override
    default String table() {
        return "exercises";
    }

    @Override
    default Exercise map(ResultSet results) {
        Exercise e = new Exercise();
        try {
            e.setId(results.getInt("id"));
            e.setExercise_name(results.getString("exercise_name"));
            e.setEquipment(Exercise.EQUIPMENT.getInstance(results.getString("equipment")));
            e.setIntensity(Exercise.INTENSITY.getInstance(results.getString("intensity")));
            e.setRegion(Exercise.REGION.getInstance(results.getString("region")));
            e.setLoaded(true);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return e;
    }

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?,?,?,?,?);";

    }

    @Override
    default String prepareUpdate() {
        return "UPDATE " + table() + " SET exercise_name = ?, intensity = ?, equipment = ?, region = ? WHERE id = ?";
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
