package com.ssa.ironyard.fitness.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.services.FitnessAccountService;

@RestController
@RequestMapping(value="/fitness")
public class FitnessAccountController {

    Logger LOGGER = LogManager.getLogger(FitnessAccountController.class);

    @Autowired
    private FitnessAccountService service;
    
    @RequestMapping(produces = "application/json", value ="/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable String username)
    {
        return service.readAccount();    
    }
    
    @RequestMapping(produces = "application/json", value ="/{username}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Account createAccount(@PathVariable String username, @PathVariable String password)
    {
        return service.insertAccount();    
    }
    
    @RequestMapping(produces = "application/json", value ="/{username}/{password}", method = RequestMethod.PUT)
    @ResponseBody
    public Account updateAccount(@PathVariable String username, @PathVariable String password)
    {
        return service.updateAccount();    
    }
    
    @RequestMapping(produces = "application/json", value ="/{username}/{password}", method = RequestMethod.DELETE)
    @ResponseBody
    public Account deleteAccount(@PathVariable String username, @PathVariable String password)
    {
        return service.deleteAccount();    
    }
}
