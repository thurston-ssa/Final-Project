package com.ssa.ironyard.fitness.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.ssa.ironyard.fitness.crypto.BCryptSecurePassword;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAO;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.WorkoutHistory;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessHistoryServiceImpl;

@RestController
@RequestMapping(value = "/fitness")
public class FitnessAccountController
{

    Logger LOGGER = LogManager.getLogger(FitnessAccountController.class);
    final FitnessAccountServiceImpl accService;
    final FitnessHistoryServiceImpl histService;

    @Autowired
    public FitnessAccountController(FitnessAccountServiceImpl s, FitnessHistoryServiceImpl w)
    {
        this.accService = s;
        this.histService = w;
    }

    @RequestMapping(value = "")
    public View homeView()
    {
        return new InternalResourceView("index.html");
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAccount(@PathVariable String username, @PathVariable String password,
            HttpSession session)
    {
        Map<String, Object> map = new HashMap<>();

        Account acc = accService.readAccount(username);

        if (acc == null)
            map.put("error", "Account/password not found");
        else if (!new BCryptSecurePassword().verify(password, acc.getPassword()))
            map.put("error", "Account/password not found");
        else
            map.put("success", acc);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{username}/{password}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> createAccount(@PathVariable String username,
            @PathVariable String password, HttpSession session)
    {
        Map<String, Object> map = new HashMap<>();
   
        Account a = accService.insertAccount(username, new BCryptSecurePassword().secureHash(password));
       
        if (a == null)
            map.put("error", "Account/password not found");
        
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

        Account updatedAccount = accService.updateAccount(a);

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

        if (accService.readAccount(id) != null)
            b = accService.deleteAccount(id);

        if (b == false)
            map.put("error", b);
        else
            map.put("success", b);

        return ResponseEntity.ok().header("Fitness Account", "Account").body(map);

    }

    @RequestMapping(produces = "application/json", value = "/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<WorkoutHistory>>> getWorkoutHistory(@PathVariable Integer id)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, List<WorkoutHistory>> map = new HashMap<>();

        List<WorkoutHistory> history = histService.readAll(id);

        if (history == null)
            map.put("error", history);
        else
            map.put("success", history);

        return ResponseEntity.ok().header("Fitness", "Workout History").body(map);

    }

    @RequestMapping(produces = "application/json", value = "/{id}/history", method = RequestMethod.POST)
    public ResponseEntity<Map<String, WorkoutHistory>> addWorkoutToHistory(@PathVariable Integer id,
            HttpServletRequest request)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, WorkoutHistory> map = new HashMap<>();

        WorkoutHistory history = new WorkoutHistory();
        history.setAccount(new Account(id));
        history.setExercise(new Exercise(request.getParameter("exercise")));
        history.setSets(Integer.parseInt(request.getParameter("sets")));
        history.setReps(Integer.parseInt(request.getParameter("reps")));
        history.setWeight(Double.parseDouble(request.getParameter("weight")));
        // history.setTime(Duration.between(0, Double.parseDouble(request.getParameter("time"))));
        // history.setWorkout_date(LocalDateTime.parse(text));
        history.setDistance(Double.parseDouble(request.getParameter("distance")));

        WorkoutHistory insertedHistory = histService.insertHistory(history);

        if (insertedHistory == null)
            map.put("error", insertedHistory);
        else
            map.put("success", insertedHistory);

        return ResponseEntity.ok().header("Fitness", "Workout History").body(map);

    }
}
