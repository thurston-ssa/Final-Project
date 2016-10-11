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

import com.ssa.ironyard.fitness.model.Regimen;

@Component
public class RegimenDAOImpl extends AbstractSpringDAO<Regimen> implements RegimenDAO {

    protected RegimenDAOImpl(ORM<Regimen> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public RegimenDAOImpl(DataSource datasource) {
        this(new RegimenORM() {
        }, datasource);
    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Regimen domainToInsert) throws SQLException {
        insertStatement.setString(1, domainToInsert.getDay().abbrev);
        insertStatement.setInt(2, domainToInsert.getSets());
        insertStatement.setInt(3, domainToInsert.getReps());
        insertStatement.setDouble(4, domainToInsert.getWeight());
        insertStatement.setDouble(5, domainToInsert.getDistance());
        insertStatement.setInt(6, domainToInsert.getTime().getNano());
        insertStatement.setInt(7, domainToInsert.getAccount().getId());
        insertStatement.setInt(8, domainToInsert.getExercise().getId());
    }

    @Override
    protected Regimen afterInsert(Regimen copy, Integer id) {
        Regimen r = copy;
        r.setId(id);
        r.setLoaded(true);
        return r;
    }

    @Override
    protected Regimen afterUpdate(Regimen copy) {
        Regimen r = copy;
        r.setLoaded(true);
        return r;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Regimen domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setString(1, domainToUpdate.getDay().abbrev);
            ps.setInt(2, domainToUpdate.getSets());
            ps.setInt(3, domainToUpdate.getReps());
            ps.setDouble(4, domainToUpdate.getWeight());
            ps.setDouble(5, domainToUpdate.getDistance());
            ps.setInt(6, domainToUpdate.getTime().getNano());
            ps.setInt(7, domainToUpdate.getAccount().getId());
            ps.setInt(8, domainToUpdate.getExercise().getId());
        };

    }

    @Override
    public List<Regimen> readByUserId(Integer id) {
        List<Regimen> temp = new ArrayList<>();
        if (null == id)
            return null;
        return this.springTemplate.query(((RegimenORM) this.orm).eagerPrepareReadByUserId(),
                (PreparedStatement ps) -> ps.setInt(1, id), (ResultSet cursor) -> {
                    while (cursor.next())
                        temp.add(((RegimenORM) this.orm).eagerExerciseMap(cursor));
                    return temp;
                });
    }
    
    public int clear() {
        return this.springTemplate.update(((RegimenORM) this.orm).clear());
    }


}
