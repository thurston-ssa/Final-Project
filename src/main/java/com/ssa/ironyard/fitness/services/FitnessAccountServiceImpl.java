package com.ssa.ironyard.fitness.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.model.Account;


@Component
public class FitnessAccountServiceImpl
{

    AccountDAOimpl daoAccount;

    @Autowired
    public FitnessAccountServiceImpl(AccountDAOimpl daoAccount)
    {

        this.daoAccount = daoAccount;

    }

    public Account readAccount(String userName)
    {

        return null;
    }

    public Account insertAccount(String username, String password)
    {
        return daoAccount.insert(new Account(username, password));
    }

//    public Account updateAccount(String username, String password, HttpServletRequest request)
//    {
//        Account a = daoAccount.read(username).clone();
//        if(a == null)
//            return new Account();
//        
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setFirstName(request.getParameter("firstName"));
//        
//        
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setAge(Integer.parseInt(request.getParameter("age")));
//        a.setAge(Integer.parseInt(request.getParameter("age")));  
//        
//        
//        
//        
//        return daoAccount.update(a);
//    }

    public Account deleteAccount()
    {

        return null;
    }

}
