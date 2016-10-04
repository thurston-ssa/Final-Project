package com.ssa.ironyard.fitness.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;

@RestController
@RequestMapping(value = "/fitness")
public class FitnessAccountController
{

    Logger LOGGER = LogManager.getLogger(FitnessAccountController.class);
    final FitnessAccountServiceImpl service;

    @Autowired
    public FitnessAccountController(FitnessAccountServiceImpl s)
    {
        this.service = s;
    }

    @RequestMapping(produces = "application/json", value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Account>> getAccount(@PathVariable String username)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, Account> map = new HashMap<>();

        Account a = service.readAccount(username);

        if (a == null)
            map.put("error", a);
        else
            map.put("success", a);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Account>> createAccount(@PathVariable String username,
            @PathVariable String password)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, Account> map = new HashMap<>();

        Account a = service.insertAccount(username, password);

        if (a == null)
            map.put("error", a);
        else
            map.put("success", a);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Account>> updateAccount(@PathVariable Integer id, HttpServletRequest request)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, Account> map = new HashMap<>();

        Account a = new Account();
        a.setId(id);
        a.setFirstName(request.getParameter("firstName"));
        a.setLastName(request.getParameter("lastName"));
        a.setAge(Integer.parseInt(request.getParameter("age")));
        a.setGender(Account.Gender.valueOf(request.getParameter("gender")));
        a.setGoal(new Goal(Goal.Type.valueOf(request.getParameter("goal"))));
        a.setHeight(Double.parseDouble(request.getParameter("height")));
        a.setWeight(Double.parseDouble(request.getParameter("weight")));

        Account updatedAccount = service.updateAccount(a);

        if (updatedAccount == null)
            map.put("error", a);
        else
            map.put("success", a);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteAccount(@PathVariable Integer id)
    {
        Boolean b = false;
        Map<String, Boolean> map = new HashMap<>();

        if (service.readAccount(id) != null)
            b = service.deleteAccount(id);

        if (b == false)
            map.put("error", b);
        else
            map.put("success", b);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);

    }
}
