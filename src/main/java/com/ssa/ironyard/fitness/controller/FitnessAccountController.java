package com.ssa.ironyard.fitness.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.ssa.ironyard.fitness.crypto.BCryptSecurePassword;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.Regimen;
import com.ssa.ironyard.fitness.model.WorkoutHistory;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessHistoryServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessRegimenServiceImpl;

@RestController
@RequestMapping(value = "/fitness/home")
public class FitnessAccountController
{

    Logger LOGGER = LogManager.getLogger(FitnessAccountController.class);
    final FitnessAccountServiceImpl accService;
    final FitnessHistoryServiceImpl histService;
    final FitnessRegimenServiceImpl regimenService;

    @Autowired
    public FitnessAccountController(FitnessAccountServiceImpl s, FitnessHistoryServiceImpl w,
            FitnessRegimenServiceImpl r)
    {
        this.accService = s;
        this.histService = w;
        this.regimenService = r;
    }

    @RequestMapping(value = "/{id}")
    public View mainView()
    {
        View main = new InternalResourceView("/index.html");
        return main;
    }

    // @RequestMapping(produces = "application/json", value = "/{username}", method = RequestMethod.GET)
    // public ResponseEntity<Map<String, Object>> getAccount(@PathVariable String username)
    // {
    // Map<String, Object> map = new HashMap<>();
    //
    // Account acc = accService.readAccount(username);
    //
    // if (acc == null)
    // map.put("error", "Account not found");
    // else
    // map.put("success", acc);
    //
    // return ResponseEntity.ok().header("Fitness Account", "Account").body(map);
    // }

    @RequestMapping(produces = "application/json", value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> createAccount(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();

        Account a = accService.insertAccount(new Account(request.getParameter("username"),
                new BCryptSecurePassword().secureHash(request.getParameter("password"))));

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
        a.setUsername(request.getParameter("username"));
        a.setFirstName(request.getParameter("firstName"));
        a.setLastName(request.getParameter("lastName"));
        a.setAge(Integer.parseInt(request.getParameter("age")));
        a.setGender(Account.Gender.valueOf(request.getParameter("gender")));
        a.setGoal(new Goal(Goal.Type.valueOf(request.getParameter("goal"))));
        a.setHeight(Double.parseDouble(request.getParameter("height")));
        a.setWeight(Double.parseDouble(request.getParameter("weight")));

        Account updatedAccount = accService.updateAccount(a);

        if (updatedAccount == null)
            map.put("error", updatedAccount);
        else
            map.put("success", updatedAccount);

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
        Map<String, List<WorkoutHistory>> map = new HashMap<>();

        List<WorkoutHistory> history = histService.readAll(id);

        if (history == null)
            map.put("error", history);
        else
            map.put("success", history);

        return ResponseEntity.ok().header("Fitness", "Workout History").body(map);

    }

    @RequestMapping(produces = "application/json", value = "/{id}/history", method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<WorkoutHistory>>> addWorkoutsToHistory(@PathVariable Integer id,
            @RequestBody WorkoutJSONRequest request)
    {
        Map<String, List<WorkoutHistory>> response = new HashMap<>();
        List<WorkoutHistory> workouts = new ArrayList<>();

        Account account = new Account(id);
        LocalDate workoutDate = request.workedOut;
        
        for (ExerciseItem exercise : request.exercises)
        {
            WorkoutHistory history = new WorkoutHistory();
            history.setAccount(account);
            history.setWorkout_date(workoutDate);
            history.setDistance(exercise.distance);
            history.setExercise(new Exercise(exercise.exerciseId));
            history.setReps(exercise.reps);
            history.setSets(exercise.sets);
            history.setTime(exercise.time);
            history.setWeight(exercise.weight);
            
            workouts.add(history);
        }

        workouts = histService.insertHistory(workouts);

        if (workouts.isEmpty())
            response.put("error", workouts);
        else
            response.put("success", workouts);

        return ResponseEntity.ok().header("Fitness", "Workout History").body(response);

    }

    @RequestMapping(produces = "application/json", value = "/{id}/regimen", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createRegimen(@PathVariable Integer id, HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();

        Regimen r1 = new Regimen();
        r1.setAccount(new Account(id));
        r1.setDay(Regimen.DAY.valueOf(request.getParameter("day")));
        //r1.setDistance(Double.parseDouble(request.getParameter("distance")));
        r1.setExercise(new Exercise(request.getParameter("exercise")));
        r1.setReps(Integer.parseInt(request.getParameter("reps")));
        r1.setSets(Integer.parseInt(request.getParameter("sets")));
        //r1.setTime(Duration.parse(request.getParameter("time")));
        //r1.setWeight(Double.parseDouble(request.getParameter("weight")));

        Regimen r2 = regimenService.insertRegimen(r1);

        if (r2 == null)
            map.put("error", "Regimen insert failed");
        else
            map.put("success", r2);

        return ResponseEntity.ok().body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{id}/regimen", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateRegimen(@PathVariable Integer id, HttpServletRequest request)
    {
        ResponseEntity.status(HttpStatus.CREATED);
        Map<String, Object> map = new HashMap<>();

        Regimen r1 = new Regimen();
        r1.setId(Integer.parseInt(request.getParameter("id")));
        r1.setAccount(new Account(id));
        r1.setDay(Regimen.DAY.valueOf(request.getParameter("day")));
        //r1.setDistance(Double.parseDouble(request.getParameter("distance")));
        r1.setExercise(new Exercise(request.getParameter("exercise")));
        r1.setReps(Integer.parseInt(request.getParameter("reps")));
        r1.setSets(Integer.parseInt(request.getParameter("sets")));
        //r1.setTime(Duration.parse(request.getParameter("time")));
        //r1.setWeight(Double.parseDouble(request.getParameter("weight")));

        Regimen r2 = regimenService.updateRegimen(r1);

        if (r2 == null)
            map.put("error", "Regimen update failed");
        else
            map.put("success", r2);

        return ResponseEntity.ok().body(map);
    }

    @RequestMapping(produces = "application/json", value = "/{id}/regimen", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Regimen>>> getRegimens(@PathVariable Integer id)
    {
        Map<String, List<Regimen>> map = new HashMap<>();

        List<Regimen> history = regimenService.readAll(id);

        if (history == null)
            map.put("error", history);
        else
            map.put("success", history);

        return ResponseEntity.ok().body(map);

    }

    @RequestMapping(produces = "application/json", value = "/{id}/regimen", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteRegimen(@PathVariable Integer id)
    {
        Boolean b = false;
        Map<String, Boolean> map = new HashMap<>();

        if (regimenService.readRegimen(id) != null)
            b = regimenService.deleteRegimen(id);

        if (b == false)
            map.put("error", b);
        else
            map.put("success", b);

        return ResponseEntity.ok().body(map);

    }
}
/* *************************************************************************************************
*                    JSON REQUEST MAPPERS
****************************************************************************************************
*/
class WorkoutJSONRequest
{
    static final DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    LocalDate workedOut;
    
    public final List<ExerciseItem> exercises = new ArrayList();
    
    public void setDate(String date)
    {
        //DateTimeFormatter.
        this.workedOut = LocalDate.parse(date, usFormatter);
    }
    
//    public void setExercises(List exercises)
//    {
//        this.exercises = exercises;
//    }
    
    public void addExerc(ExerciseItem exercise)
    {
        this.exercises.add(exercise);
        //return this.exercises;
    }
}

class ExerciseItem
{
    BigDecimal weight, time, distance;
    Integer exerciseId, reps, sets;

    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }

    public void setDistance(BigDecimal distance)
    {
        this.distance = distance;
    }
   
   
    public void setTime(BigDecimal time)
    {
        this.time = time;
    }

    public void setExerciseId(Integer exerciseId)
    {
        this.exerciseId = exerciseId;
    }

    public void setReps(Integer reps)
    {
        this.reps = reps;
    }

    public void setSets(Integer sets)
    {
        this.sets = sets;
    }
    
    
}
