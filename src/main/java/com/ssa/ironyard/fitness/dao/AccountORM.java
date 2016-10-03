package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;

import com.ssa.ironyard.fitness.model.Account;

public interface AccountORM extends ORM<Account> {

    default String projection() {
        return table() + ".id, username, password, first_name, last_name, height, weight, sex, age, goal_id, history_id";
    };

    default String eagerProjection(){
        return table() + ".id, username, password, first_name, last_name, height, weight, sex, age, goal_id, history_id"
                + "";
    }

    default String table() {
        return "accounts";
    }

    Account map(ResultSet results);

    String prepareInsert();

    String prepareUpdate();

    String prepareRead();

    String prepareDelete();

    String prepareSearch();

    String prepareEagerRead();

    Account eagerMap(ResultSet results);
}
