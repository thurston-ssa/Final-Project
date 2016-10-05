package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.model.Exercise;
@Component
public class ExerciseDAOImpl extends AbstractSpringDAO<Exercise> implements ExerciseDAO{

    protected ExerciseDAOImpl(ORM<Exercise> orm, DataSource dataSource) {
        super(orm, dataSource);
    }
    
    @Autowired
    public ExerciseDAOImpl(DataSource datasource) {
        this(new ExerciseORM() {}, datasource);
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Exercise domainToInsert) throws SQLException {
        
        insertStatement.setString(2, domainToInsert.getExercise_name());
        insertStatement.setString(3, domainToInsert.getEquipment().abbrev);
        insertStatement.setString(4, domainToInsert.getIntensity().abbrev);
        insertStatement.setString(5, domainToInsert.getRegion().abbrev);

    }

    @Override
    protected Exercise afterInsert(Exercise copy, Integer id) {
        Exercise e = copy.clone();
        e.setId(id);
        return e;
    }

    @Override
    protected Exercise afterUpdate(Exercise copy) {
        Exercise e = copy.clone();
        return e;
    }

    
    @Override
    protected PreparedStatementSetter updatePreparer(Exercise domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setInt(1, domainToUpdate.getId());
            ps.setString(2, domainToUpdate.getExercise_name());
            ps.setString(3, domainToUpdate.getEquipment().abbrev);
            ps.setString(4, domainToUpdate.getIntensity().abbrev);
            ps.setString(5, domainToUpdate.getRegion().abbrev);
      
        };
    }

}