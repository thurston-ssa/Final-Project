package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.ssa.ironyard.fitness.model.Account;

public interface AccountORM extends ORM<Account> {

    default String projection() {
        return table()
                + ".id, username, password, first_name, last_name, height, weight, sex, age, goal_id, history_id";
    };

    default String eagerProjection() {
        return table()
                + ".id, username, password, first_name, last_name, height, weight, sex, age, goal_id, history_id";
    }

    default String table() {

        return "accounts";
    }

    default Account map(ResultSet results){
        Account a = new Account();
        try {
            a.setAge(results.getInt("age"));
            a.setFirstName(results.getString("first_name"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    };

    default String prepareInsert() {
        return "INSERT INTO " + table() + " (" + projection() + ") VALUES(?,?,?,?,?,?,?,?,?,?,?);";

    };

    default String prepareUpdate() {
        return "UPDATE " + table() + " SET username = ?, password = ?, first_name = ?, "
                + "last_name = ?, height =?, weight = ?, sex =?, age = ?, goal_id = ?, history_id = ? WHERE id = ?";
    };

    default String prepareRead() {

        return "SELECT " + projection() + "FROM " + table() + "WHERE id=?";

    }

    default String prepareDelete() {
        
        return "DELETE FROM " + table() + " WHERE id = ?";

    };

    default String prepareEagerRead(){
      return ":D";  
    };

    Account eagerMap(ResultSet results);
}
