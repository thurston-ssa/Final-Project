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
            g.setType(Goal.Type.valueOf(results.getString("goal")));
            g.setLoaded(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return g;
    }

    @Override
    default String prepareInsert() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default public String prepareUpdate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default public String prepareRead() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default public String prepareDelete() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default public String prepareReadByUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default public String eagerPrepareReadByUsername() {
        // TODO Auto-generated method stub
        return null;
    }
}
