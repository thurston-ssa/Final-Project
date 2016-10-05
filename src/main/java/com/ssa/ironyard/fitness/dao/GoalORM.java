package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssa.ironyard.fitness.model.Goal;

public interface GoalORM extends ORM<Goal> {
    @Override
    default String projection() {

        return "goal";
    }

    @Override
    default String table() {
        return "goals";
    }

    @Override
    default Goal map(ResultSet results) {
        Goal g = new Goal();
        try {
            g.setId(results.getInt("id"));
            g.setType(Goal.Type.getInstance(results.getString("goal")));
            g.setLoaded(true);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return g;
    }

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?);";

    }

    @Override
    default  String prepareUpdate() {
        return "UPDATE " + table() + "SET goal = ? WHERE id = ?";
    }

    @Override
    default  String prepareRead() {
        return "SELECT " + table()+".id,goal FROM " + table() + " WHERE id = ?" ;
    }

    @Override
    default  String prepareDelete() {
        return "DELETE FROM " + table() + " WHERE id = ?";
    }
    default String clear() {
        return "DELETE FROM " + table();
    }
}
