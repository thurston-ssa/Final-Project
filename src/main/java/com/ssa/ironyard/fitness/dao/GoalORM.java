package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssa.ironyard.fitness.model.Goal;

public interface GoalORM extends ORM<Goal> {
    @Override
    default String projection() {

        return table() + ".id, goal";
    }

    @Override
    default String table() {
        return "goal";
    }

    @Override
    default Goal map(ResultSet results) {
        Goal g = new Goal();
        try {
            g.setId(results.getInt("id"));
            g.setType(Goal.Type.getInstance((results.getString("goal"))));
            g.setLoaded(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return g;
    }

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?,?);";

    }

    @Override
    default public String prepareUpdate() {
        return "UPDATE " + table() + "SET goal = ? WHERE id = ?";
    }

    @Override
    default public String prepareRead() {
        return "SELECT " + projection() + " FROM " + table() + "WHERE id = ?" ;
    }

    @Override
    default public String prepareDelete() {
        return "DELETE FROM " + table() + " WHERE id = ?";
    }
    

    @Override
    default public String prepareReadByUsername() {
        return null;
    }

    @Override
    default public String eagerPrepareReadByUsername() {
        return null;
    }
}
