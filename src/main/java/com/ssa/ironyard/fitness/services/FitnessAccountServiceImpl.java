package com.ssa.ironyard.fitness.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Password;

@Component
public class FitnessAccountServiceImpl
{

    AccountDAOimpl daoAccount;

    @Autowired
    public FitnessAccountServiceImpl(AccountDAOimpl daoAccount)
    {
        this.daoAccount = daoAccount;
    }

    @Transactional
    public Account readAccount(String username)
    {
        return daoAccount.readByUsername(username);
    }

    @Transactional
    public Account readAccount(Integer id)
    {
        return daoAccount.read(id);
    }

    @Transactional
    public Account insertAccount(String username, Password password)
    {
        return daoAccount.insert(new Account(username, password));
    }

    @Transactional
    public Account updateAccount(Account account)
    {
        return daoAccount.update(account);
    }

    @Transactional
    public boolean deleteAccount(Integer id)
    {
        return daoAccount.delete(id);
    }

}
