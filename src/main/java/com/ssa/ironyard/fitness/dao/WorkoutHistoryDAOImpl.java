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

        mapDomainToPreparedStatement(insertStatement, domainToInsert, 1);

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

            int lastParameter = mapDomainToPreparedStatement(ps, domainToUpdate, 1);
            ps.setInt(lastParameter, domainToUpdate.getId());

        };
    }

    static int mapDomainToPreparedStatement(PreparedStatement preparedStatement, WorkoutHistory history,
            int parameterIndex) throws SQLException {

        preparedStatement.setTimestamp(parameterIndex++, Timestamp.valueOf(history.getWorkout_date()));
        preparedStatement.setInt(parameterIndex++, history.getSets());
        preparedStatement.setInt(parameterIndex++, history.getReps());
        preparedStatement.setDouble(parameterIndex++, history.getWeight());
        preparedStatement.setDouble(parameterIndex++, history.getDistance());
        preparedStatement.setInt(parameterIndex++, history.getTime().getNano());
        preparedStatement.setInt(parameterIndex++, history.getAccount().getId());
        preparedStatement.setInt(parameterIndex++, history.getExercise().getId());

        return parameterIndex;
    }

}
