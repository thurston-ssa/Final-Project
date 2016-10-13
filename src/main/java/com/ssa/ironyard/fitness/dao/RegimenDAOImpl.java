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

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Regimen domainToInsert) throws SQLException {

        mapDomainToPreparedStatement(insertStatement, domainToInsert, 1);

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
            int lastParameter = mapDomainToPreparedStatement(ps, domainToUpdate, 1);
            ps.setInt(lastParameter, domainToUpdate.getId());
        };

    }

    static int mapDomainToPreparedStatement(PreparedStatement preparedStatement, Regimen regimen, int parameterIndex)
            throws SQLException {

        preparedStatement.setString(parameterIndex++, regimen.getDay().abbrev);
        preparedStatement.setInt(parameterIndex++, regimen.getSets());
        preparedStatement.setInt(parameterIndex++, regimen.getReps());
        preparedStatement.setBigDecimal(parameterIndex++, regimen.getWeight());
        preparedStatement.setBigDecimal(parameterIndex++, regimen.getDistance());
        preparedStatement.setBigDecimal(parameterIndex++, regimen.getTime());
        preparedStatement.setInt(parameterIndex++, regimen.getAccount().getId());
        preparedStatement.setInt(parameterIndex++, regimen.getExercise().getId());

        return parameterIndex;
    }

}
