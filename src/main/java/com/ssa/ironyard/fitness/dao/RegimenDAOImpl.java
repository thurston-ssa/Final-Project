package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ssa.ironyard.fitness.model.Regimen;

public class RegimenDAOImpl extends AbstractSpringDAO<Regimen> implements RegimenDAO{

    protected RegimenDAOImpl(ORM<Regimen> orm, DataSource dataSource) {
        super(orm, dataSource);
    }
    
    @Autowired
    public RegimenDAOImpl(DataSource datasource) {
        this(new RegimenORM(){}, datasource);
    }
    
    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Regimen domainToInsert) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Regimen afterInsert(Regimen copy, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Regimen afterUpdate(Regimen copy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Regimen domainToUpdate) {
        // TODO Auto-generated method stub
        return null;
    }

}
