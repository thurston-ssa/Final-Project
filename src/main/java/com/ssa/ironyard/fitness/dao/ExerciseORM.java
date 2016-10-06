package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;

import com.ssa.ironyard.fitness.model.Exercise;

public interface ExerciseORM extends ORM<Exercise> {

    @Override
    default String projection() {
        StringJoiner joiner = new StringJoiner(", " + table() + ".", table() + ".", "");
        joiner.add("id").add("exercise_name").add("equipment").add("intensity").add("region");
        return joiner.toString();
    }

    @Override
    default String table() {
        return "exercises";
    }

    @Override
    default Exercise map(ResultSet results) {
        Exercise e = new Exercise();
        try {
            final String columnPrefix = table() + ".";
            e.setId(results.getInt(columnPrefix + "id"));
            e.setExercise_name(results.getString(columnPrefix + "exercise_name"));
            e.setEquipment(Exercise.EQUIPMENT.getInstance(results.getString(columnPrefix + "equipment")));
            e.setIntensity(Exercise.INTENSITY.getInstance(results.getString(columnPrefix + "intensity")));
            e.setRegion(Exercise.REGION.getInstance(results.getString(columnPrefix + "region")));
            e.setLoaded(true);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return e;
    }

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection().substring(13) + ") VALUES(?,?,?,?);";

    }

    @Override
    default String prepareUpdate() {
        return "UPDATE " + table() + " SET " + table() + ".exercise_name = ?, " + table() + ".intensity = ?, " + table()
                + ".equipment = ?, " + table() + ".region = ? WHERE " + table() + ".id = ?";
    }

    @Override
    default String prepareRead() {
        return "SELECT " + projection() + " FROM " + table() + " WHERE " + table() + ".id=?";
    }

    @Override
    default String prepareDelete() {
        return "DELETE FROM " + table() + " WHERE " + table() + ".id = ?";
    }

    default String clear() {
        return "DELETE FROM " + table();

    };

    default String readAll() {
        ;
        return "SELECT * FROM " + table();
    }

}
