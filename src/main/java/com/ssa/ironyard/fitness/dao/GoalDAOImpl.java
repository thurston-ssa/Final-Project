package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.model.Goal;

@Component
public class GoalDAOImpl extends AbstractSpringDAO<Goal> implements GoalDAO {

    protected GoalDAOImpl(ORM<Goal> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public GoalDAOImpl(DataSource datasource) {
        this(new GoalORM() {
        }, datasource);
    }

    public int clear() {
        return this.springTemplate.update(((GoalORM) this.orm).clear());
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Goal domainToInsert) throws SQLException {
        insertStatement.setString(1, (domainToInsert.getType().abbrev));
        
    }

    @Override
    protected Goal afterInsert(Goal copy, Integer id) {
        Goal g = copy.clone();
        g.setId(id);
        g.setLoaded(true);
        return g;
    }

    @Override
    protected Goal afterUpdate(Goal copy) {
        Goal g = copy.clone();
        g.setLoaded(true);
        return g;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Goal domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setString(2, domainToUpdate.getType().abbrev);
            ps.setInt(1, domainToUpdate.getId());
        };
    }

}
