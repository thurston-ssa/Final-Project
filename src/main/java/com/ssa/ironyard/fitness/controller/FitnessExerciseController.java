package com.ssa.ironyard.fitness.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessExerciseServiceImpl;

@RestController
@RequestMapping(value = "/fitness")
public class FitnessExerciseController
{

    Logger LOGGER = LogManager.getLogger(FitnessExerciseController.class);

    @Autowired
    private FitnessExerciseServiceImpl service;

    @RequestMapping(produces = "application/json", value = "/exercises", method = RequestMethod.GET)
    public ResponseEntity<List<Exercise>> getExerciseList(@PathVariable String username)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        HashMap<String, List<Exercise>> map = new HashMap<String, List<Exercise>>();
        List<Exercise> list = service.readAllExercises();

        if (list.size() == 0)
            map.put("error", list);
        else
            map.put("success", list);
        
        return ResponseEntity.ok().header("SSA_Bank Customer", "Account").body(list);

    }

  
 

    // @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.DELETE)
    // public ResponseEntity<Map<String, Account>> deleteAccount(@PathVariable String username,
    // @PathVariable String password)
    // {
    // if (service.readAccount(username) != null)
    // return service.deleteAccount(username, password);
    //
    // }
}
