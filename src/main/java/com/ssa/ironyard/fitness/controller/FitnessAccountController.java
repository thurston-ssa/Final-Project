package com.ssa.ironyard.fitness.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;

@RestController
@RequestMapping(value = "/fitness")
public class FitnessAccountController
{

    Logger LOGGER = LogManager.getLogger(FitnessAccountController.class);

    @Autowired
    private FitnessAccountServiceImpl service;

    @RequestMapping(produces = "application/json", value = "/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable String username)
    {
        return service.readAccount(userName);
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Account createAccount(@PathVariable String username, @PathVariable String password)
    {
        return service.insertAccount(username, password);
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.PUT)
    @ResponseBody
    public Account updateAccount(@PathVariable String username, @PathVariable String password, HttpServletRequest request)
    {
        return service.updateAccount(username, password, request);
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.DELETE)
    @ResponseBody
    public Account deleteAccount(@PathVariable String username, @PathVariable String password)
    {
        return service.deleteAccount();
    }
}
