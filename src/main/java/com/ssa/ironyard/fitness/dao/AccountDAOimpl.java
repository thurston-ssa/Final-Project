package com.ssa.ironyard.fitness.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.model.Account;

@Component
public class AccountDAOimpl extends AbstractSpringDAO<Account> implements AccountDAO {

    protected AccountDAOimpl(ORM<Account> orm, DataSource dataSource) {
        super(orm, dataSource);
    }

    @Autowired
    public AccountDAOimpl(DataSource datasource) {
        this(new AccountORM() {
        }, datasource);
    }

    @Override
    public Account readByUsername(String username) {
        if (null == username)
            return null;
        return this.springTemplate.query(((AccountORM) this.orm).eagerPrepareReadByUsername(),
                (PreparedStatement ps) -> ps.setString(1, username), (ResultSet cursor) -> {
                    if (cursor.next())
                        return this.orm.map(cursor);
                    return null;
                });
    }

    @Override
    public Account eagerRead(Integer id) {
        if (null == id)
            return null;
        return this.springTemplate.query(((AccountORM) this.orm).eagerPrepareReadByUsername(),
                (PreparedStatement ps) -> ps.setInt(1, id), (ResultSet cursor) -> {
                    if (cursor.next())
                        return this.orm.map(cursor);
                    return null;
                });

    }

    @Override
    protected void insertPreparer(PreparedStatement insertStatement, Account domainToInsert) throws SQLException {
        insertStatement.setString(2, domainToInsert.getUsername());
        insertStatement.setString(3, domainToInsert.getPassword().getSalt());
        insertStatement.setString(4, domainToInsert.getPassword().getHash());
        insertStatement.setString(5, domainToInsert.getFirstName());
        insertStatement.setString(6, domainToInsert.getLastName());
        insertStatement.setDouble(7, domainToInsert.getHeight());
        insertStatement.setDouble(8, domainToInsert.getWeight());
        insertStatement.setString(9, String.valueOf(domainToInsert.getGender().abbrev));
        insertStatement.setInt(10, domainToInsert.getAge());
        insertStatement.setInt(11, domainToInsert.getGoal().getId());

    }

    @Override
    protected Account afterInsert(Account copy, Integer id) {
        Account a = copy.clone();
        a.setId(id);
        return a;
    }

    @Override
    protected Account afterUpdate(Account copy) {
        Account a = copy.clone();
        return a;
    }

    @Override
    protected PreparedStatementSetter updatePreparer(Account domainToUpdate) {
        return (PreparedStatement ps) -> {
            ps.setString(2, domainToUpdate.getUsername());
            ps.setString(3, domainToUpdate.getPassword().getSalt());
            ps.setString(4, domainToUpdate.getPassword().getHash());
            ps.setString(5, domainToUpdate.getFirstName());
            ps.setString(6, domainToUpdate.getLastName());
            ps.setDouble(7, domainToUpdate.getHeight());
            ps.setDouble(8, domainToUpdate.getWeight());
            ps.setString(9, String.valueOf(domainToUpdate.getGender().abbrev));
            ps.setInt(10, domainToUpdate.getAge());
            ps.setInt(11, domainToUpdate.getGoal().getId());
        };
    }


}
