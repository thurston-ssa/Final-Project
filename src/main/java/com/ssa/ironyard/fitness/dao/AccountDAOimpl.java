package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ssa.ironyard.fitness.model.Account;

public class AccountDAOimpl extends AbstractSpringDAO<Account> {

    final ORM<Account> orm;
    final DataSource dataSource;

    protected AccountDAOimpl(ORM<Account> orm, DataSource dataSource) {
        super(orm, dataSource);
    }
    
    @Autowired
    public AccountDAOimpl(DataSource datasource) {
    this(new AccountORM() {
    }, datasource);
    }
    

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Account domainToInsert) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    protected Account afterInsert(Account copy, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Account afterUpdate(Account copy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Account domainToUpdate) {
        // TODO Auto-generated method stub
        return null;
    }

}
