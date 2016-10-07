package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.model.Exercise;

@Component
public class ExerciseDAOImpl extends AbstractSpringDAO<Exercise> implements ExerciseDAO {

    protected ExerciseDAOImpl(ORM<Exercise> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public ExerciseDAOImpl(DataSource datasource) {
        this(new ExerciseORM() {
        }, datasource);
    }

    public List<Exercise> readAll() {
        List<Exercise> temp = new ArrayList<>();
        return this.springTemplate.query(((ExerciseORM) this.orm).readAll(), (ResultSet cursor) -> {
            while (cursor.next())
                temp.add(this.orm.map(cursor));
            return temp;

        });
    }

    public int clear() {
        return this.springTemplate.update(((ExerciseORM) this.orm).clear());
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Exercise domainToInsert) throws SQLException {

        insertStatement.setString(1, domainToInsert.getExercise_name());
        insertStatement.setString(2, domainToInsert.getCategory());
        insertStatement.setString(3, domainToInsert.getMuscles());
        insertStatement.setString(4, domainToInsert.getImage());
        insertStatement.setString(5, domainToInsert.getInstructions());

    }

    @Override
    protected Exercise afterInsert(Exercise copy, Integer id) {
        Exercise e = copy.clone();
        e.setId(id);
        e.setLoaded(true);
        return e;
    }

    @Override
    protected Exercise afterUpdate(Exercise copy) {
        Exercise e = copy.clone();
        e.setLoaded(true);
        return e;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Exercise domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setInt(1, domainToUpdate.getId());
            ps.setString(2, domainToUpdate.getExercise_name());
            ps.setString(2, domainToUpdate.getCategory());
            ps.setString(3, domainToUpdate.getMuscles());
            ps.setString(4, domainToUpdate.getImage());
            ps.setString(5, domainToUpdate.getInstructions());

        };
    }

}
