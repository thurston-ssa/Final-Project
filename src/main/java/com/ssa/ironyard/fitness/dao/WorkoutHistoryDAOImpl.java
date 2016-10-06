package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public int clear() {
        return this.springTemplate.update(((WorkoutHistoryORM) this.orm).clear());
    }

    @Override
    public List<WorkoutHistory> readByUserId(Integer id) {
        List<WorkoutHistory> temp = new ArrayList<>();
        if (null == id)
            return null;
        return this.springTemplate.query(((WorkoutHistoryORM) this.orm).eagerPrepareReadByUserId(),
                (PreparedStatement ps) -> ps.setInt(1, id), (ResultSet cursor) -> {
                    while (cursor.next())
                        temp.add(((WorkoutHistoryORM) this.orm).eagerExerciseMap(cursor));
                    return temp;
                });
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, WorkoutHistory domainToInsert)
            throws SQLException {

        insertStatement.setTimestamp(1, Timestamp.valueOf(domainToInsert.getWorkout_date()));
        insertStatement.setInt(2, domainToInsert.getSets());
        insertStatement.setInt(3, domainToInsert.getReps());
        insertStatement.setDouble(4, domainToInsert.getWeight());
        insertStatement.setDouble(5, domainToInsert.getDistance());
        insertStatement.setInt(6, domainToInsert.getTime().getNano());
        insertStatement.setInt(7, domainToInsert.getAccount().getId());
        insertStatement.setInt(8, domainToInsert.getExercise().getId());

    }

    @Override
    protected WorkoutHistory afterInsert(WorkoutHistory copy, Integer id) {
        WorkoutHistory wh = copy.clone();
        wh.setId(id);
        wh.setLoaded(true);
        return wh;
    }

    @Override
    protected WorkoutHistory afterUpdate(WorkoutHistory copy) {
        WorkoutHistory wh = copy.clone();
        wh.setLoaded(true);
        return wh;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(WorkoutHistory domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setInt(1, domainToUpdate.getId());
            ps.setString(2, domainToUpdate.getWorkout_date().toString());
            ps.setInt(3, domainToUpdate.getSets());
            ps.setInt(4, domainToUpdate.getReps());
            ps.setDouble(5, domainToUpdate.getWeight());
            ps.setDouble(6, domainToUpdate.getDistance());
            ps.setTimestamp(7, Timestamp.valueOf(domainToUpdate.getTime().toString()));
            ps.setInt(8, domainToUpdate.getAccount().getId());
            ps.setInt(9, domainToUpdate.getExercise().getId());

        };
    }

}
