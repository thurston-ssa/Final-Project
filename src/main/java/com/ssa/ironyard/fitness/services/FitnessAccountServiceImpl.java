package com.ssa.ironyard.fitness.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssa.ironyard.model.Customer;

import com.ssa.ironyard.fitness.model.Account;

@Component
public class FitnessAccountServiceImpl
{

    AccountDAOImpl daoAccount;

    @Autowired
    public FitnessAccountServiceImpl(AccountDAOImpl daoAccount)
    {

        this.daoAccount = daoAccount;

    }

    public Account readAccount()
    {

        return null;
    }

    public Account insertAccount(String username, String password)
    {
        return daoAccount.insert(new Account(username, password));
    }

    public Account updateAccount(Account a)
    {
        if(daoAccount.read(a.getId()) == null)
            return new Account();
        
        return daoAccount.update(new Account(a.getId(), username, password));
    }

    public Account deleteAccount()
    {

        return null;
    }

}
