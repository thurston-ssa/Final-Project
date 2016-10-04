package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Goal;

public class GoalDAOImpl extends AbstractSpringDAO<Goal> implements GoalDAO {

    protected GoalDAOImpl(ORM<Goal> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public GoalDAOImpl(DataSource datasource) {
        this(new GoalORM() {
        }, datasource);
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Goal domainToInsert) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    protected Goal afterInsert(Goal copy, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Goal afterUpdate(Goal copy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Goal domainToUpdate) {
        // TODO Auto-generated method stub
        return null;
    }
}
