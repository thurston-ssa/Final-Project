package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ssa.ironyard.fitness.model.WeeklyScore;

public class WeeklyScoreDAOImpl extends AbstractSpringDAO<WeeklyScore> implements WeeklyScoreDAO {

    protected WeeklyScoreDAOImpl(ORM<WeeklyScore> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public WeeklyScoreDAOImpl(DataSource datasource) {
        this(new WeeklyScoreORM(){}, datasource);
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, WeeklyScore domainToInsert) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    protected WeeklyScore afterInsert(WeeklyScore copy, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected WeeklyScore afterUpdate(WeeklyScore copy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(WeeklyScore domainToUpdate) {
        // TODO Auto-generated method stub
        return null;
    }

}
