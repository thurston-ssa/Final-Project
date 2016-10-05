package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.Password;

public interface AccountORM extends ORM<Account> {

    @Override
    default String projection() {
        // Implement String joiner
        return table() + ".id, username, salt, hash, first_name, last_name, height, weight, gender, age, goal_id";
    };

    @Override
    default String table() {

        return "accounts";
    }

    @Override
    default Account map(ResultSet results) {
        Account a = new Account();
        Goal g = new Goal();
        Password p;
        try {
            g.setId(results.getInt("goal_id"));
            a.setGoal(g);
                
            p = new Password(results.getString("salt"), results.getString("hash"));
            a.setPassword(p);

            a.setId(results.getInt("id"));
            a.setAge(results.getInt("age"));
            a.setFirstName(results.getString("first_name"));
            a.setLastName(results.getString("last_name"));
            a.setGender(Account.Gender.getInstance(results.getString("gender").charAt(0)));
            a.setHeight(results.getDouble("height"));
            a.setWeight(results.getDouble("weight"));
            
            a.setUsername(results.getString("username"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    };

    @Override
    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?,?,?,?,?,?,?,?,?,?,?);";

    };

    @Override
    default String prepareUpdate() {
        return "UPDATE " + table() + " SET username = ?, password = ?, first_name = ?, "
                + "last_name = ?, height =?, weight = ?, sex =?, age = ?, goal_id = ?, history_id = ? WHERE id = ?";
    };

    default String prepareReadByUsername() {
        return "SELECT " + projection() + " FROM " + table() + " WHERE username=?";
    }

    default String eagerPrepareReadByUsername() {
        return "SELECT " + eagerProjection() + " FROM " + table() + " INNER JOIN goal " + "ON goal.id = " + table()
                + ".goal_Id" + " INNER JOIN history" + " ON history.id = " + table() + ".history_id" + "WHERE" + table()
                + ".username= ?";
    };

    @Override
    default String prepareRead() {

        return "SELECT " + projection() + " FROM " + table() + " WHERE id=?";

    }

    @Override
    default String prepareDelete() {

        return "DELETE FROM " + table() + " WHERE id = ?";

    };

    default String eagerProjection() {
        // Implement String joiner
        return table() + projection() + "goal";
    }

    default String prepareEagerRead() {
        return "SELECT " + eagerProjection() + " FROM " + table() + " INNER JOIN goal " + "ON goal.id = " + table()
                + ".goal_Id" + "WHERE" + table() + ".id = ?";
    };

    default Account eagerMap(ResultSet results) {

        Account a = new Account();
        Goal g = new Goal();
        Password p;
        try {
            g.setId(results.getInt("goal_id"));
            g.setLoaded(true);
            g.setType(Goal.Type.valueOf(results.getString("goal")));
            a.setGoal(g);
            
            p = new Password(results.getString("salt"), results.getString("hash"));
            a.setPassword(p);

            a.setId(results.getInt("id"));
            a.setAge(results.getInt("age"));
            a.setFirstName(results.getString("first_name"));
            a.setLastName(results.getString("last_name"));
            a.setGender(Account.Gender.getInstance(results.getString("gender").charAt(0)));
            a.setHeight(results.getDouble("height"));
            a.setWeight(results.getDouble("weight"));
            a.setUsername(results.getString("username"));
            a.setLoaded(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    };
}
