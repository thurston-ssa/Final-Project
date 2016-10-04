package com.ssa.ironyard.fitness.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Account.Gender;
import com.ssa.ironyard.fitness.model.Goal;


@Component
public class FitnessAccountServiceImpl
{

    AccountDAOimpl daoAccount;

    @Autowired
    public FitnessAccountServiceImpl(AccountDAOimpl daoAccount)
    {
        this.daoAccount = daoAccount;
    }

    public Account readAccount(String username)
    {
        return daoAccount.readByUsername(username);
    }
    
    public Account readAccount(Integer id)
    {
        return daoAccount.read(id);
    }

    public Account insertAccount(String username, String password)
    {
        return daoAccount.insert(new Account(username, password));
    }

    public Account updateAccount(Account account)
    {
        return daoAccount.update(account);
    }


    public boolean deleteAccount(Integer id)
    {
        return daoAccount.delete(id);
    }

}
