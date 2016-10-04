package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.model.WorkoutHistory;

@Component
public class WorkoutHistoryDAOImpl extends AbstractSpringDAO<WorkoutHistory> implements WorkoutHistoryDAO {

    protected WorkoutHistoryDAOImpl(ORM<WorkoutHistory> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public WorkoutHistoryDAOImpl(DataSource datasource) {
        this(new WorkoutHistoryORM() {
        }, datasource);
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, WorkoutHistory domainToInsert)
            throws SQLException {
        insertStatement.setString(2, domainToInsert.getWorkout_date().toString());
        insertStatement.setInt(3, domainToInsert.getSets());
        insertStatement.setInt(4, domainToInsert.getReps());
        insertStatement.setDouble(5, domainToInsert.getWeight());
        insertStatement.setDouble(6, domainToInsert.getDistance());
        insertStatement.setTimestamp(7, Timestamp.valueOf(domainToInsert.getTime().toString()));

    }

    @Override
    protected WorkoutHistory afterInsert(WorkoutHistory copy, Integer id) {
        WorkoutHistory wh = copy.clone();
        wh.setId(id);
        return wh;
    }

    @Override
    protected WorkoutHistory afterUpdate(WorkoutHistory copy) {
        WorkoutHistory wh = copy.clone();
        
        return wh;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(WorkoutHistory domainToUpdate) {
        return (PreparedStatement ps)->{
            ps.setInt(1, domainToUpdate.getId());
            ps.setString(2, domainToUpdate.getWorkout_date().toString());
            ps.setInt(3, domainToUpdate.getSets());
            ps.setInt(4, domainToUpdate.getReps());
            ps.setDouble(5, domainToUpdate.getWeight());
            ps.setDouble(6, domainToUpdate.getDistance());
            ps.setTimestamp(7, Timestamp.valueOf(domainToUpdate.getTime().toString()));
        };
    }

}
