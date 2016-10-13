package com.ssa.ironyard.fitness.controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.WorkoutHistory;
import com.ssa.ironyard.fitness.model.WorkoutLogThingy;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessHistoryServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessRegimenServiceImpl;

public class FitnessAccountControllerTest
{

    FitnessAccountServiceImpl accService;
    FitnessHistoryServiceImpl histService;
    FitnessRegimenServiceImpl regimenService;
    FitnessAccountController controller;

    Account a;

    @Before
    public void mock()
    {
        this.accService = EasyMock.createNiceMock(FitnessAccountServiceImpl.class);
        this.histService = EasyMock.createNiceMock(FitnessHistoryServiceImpl.class);
        this.regimenService = EasyMock.createNiceMock(FitnessRegimenServiceImpl.class);

        this.controller = new FitnessAccountController(accService, histService, regimenService);

        a = new Account();
        a.setId(2);
        a.setAge(25);
        a.setFirstName("Joe");
        a.setLastName("Brown");
        a.setGender(Account.Gender.Male);
        a.setGoal(new Goal(Goal.Type.Strength));
        a.setHeight(5.11);
        a.setWeight(230.5);
        a.setUsername("Joe123");
        a.setPassword(null);
        a.setLoaded(true);

    }

    @Test
    public void updateTestSuccess() throws URISyntaxException
    {
        Account localAcc = new Account();
        localAcc.setId(2);
        localAcc.setAge(25);
        localAcc.setFirstName("Yo");
        localAcc.setLastName("Rown");
        localAcc.setGender(Account.Gender.Male);
        localAcc.setGoal(new Goal(Goal.Type.Strength));
        localAcc.setHeight(5.5);
        localAcc.setWeight(25.6);
        localAcc.setUsername("userNAMEEE");
        // BCryptSecurePassword crypt = new BCryptSecurePassword();
        // Password p = crypt.secureHash("password");
        // localAcc.setPassword(p);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addParameter("id", localAcc.getId().toString());
        mockRequest.addParameter("username", localAcc.getUsername());
        // mockRequest.addParameter("password", "password");
        mockRequest.addParameter("firstName", localAcc.getFirstName());
        mockRequest.addParameter("lastName", localAcc.getLastName());
        mockRequest.addParameter("age", localAcc.getAge().toString());
        mockRequest.addParameter("gender", localAcc.getGender().toString());
        mockRequest.addParameter("goal", localAcc.getGoal().getType().toString());
        mockRequest.addParameter("weight", localAcc.getWeight().toString());
        mockRequest.addParameter("height", localAcc.getHeight().toString());

        Capture<Account> capturedAcc = Capture.<Account>newInstance();

        EasyMock.expect(this.accService.updateAccount(EasyMock.capture(capturedAcc))).andReturn(a);
        EasyMock.replay(this.accService);

        ResponseEntity<Map<String, Account>> accountMap = this.controller.updateAccount(a.getId(), mockRequest);
        Account retAccount = accountMap.getBody().get("success");

        assertTrue(accountMap.getBody().containsKey("success"));
        assertTrue(a.deeplyEquals(retAccount));
        assertTrue(localAcc.deeplyEquals(capturedAcc.getValue()));

        EasyMock.verify(this.accService);
    }

    @Test
    public void updateTestNullFail() throws URISyntaxException
    {
        Account localAcc = new Account();
        localAcc.setId(2);
        localAcc.setAge(25);
        localAcc.setFirstName("Yo");
        localAcc.setLastName("Rown");
        localAcc.setGender(Account.Gender.Male);
        localAcc.setGoal(new Goal(Goal.Type.Strength));
        localAcc.setHeight(5.5);
        localAcc.setWeight(25.6);
        localAcc.setUsername("userNAMEEE");
        // BCryptSecurePassword crypt = new BCryptSecurePassword();
        // Password p = crypt.secureHash("password");
        // localAcc.setPassword(p);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addParameter("id", localAcc.getId().toString());
        mockRequest.addParameter("username", localAcc.getUsername());
        // mockRequest.addParameter("password", "password");
        mockRequest.addParameter("firstName", localAcc.getFirstName());
        mockRequest.addParameter("lastName", localAcc.getLastName());
        mockRequest.addParameter("age", localAcc.getAge().toString());
        mockRequest.addParameter("gender", localAcc.getGender().toString());
        mockRequest.addParameter("goal", localAcc.getGoal().getType().toString());
        mockRequest.addParameter("weight", localAcc.getWeight().toString());
        mockRequest.addParameter("height", localAcc.getHeight().toString());

        Capture<Account> capturedAcc = Capture.<Account>newInstance();

        EasyMock.expect(this.accService.updateAccount(EasyMock.capture(capturedAcc))).andReturn(null);
        EasyMock.replay(this.accService);

        ResponseEntity<Map<String, Account>> accountMap = this.controller.updateAccount(a.getId(), mockRequest);
        Account retAccount = accountMap.getBody().get("error");

        assertTrue(accountMap.getBody().containsKey("error"));
        assertTrue(!a.deeplyEquals(retAccount));
        assertTrue(localAcc.deeplyEquals(capturedAcc.getValue()));

        EasyMock.verify(this.accService);
    }
    
    @Test
    public void addWorkoutHistoryTestSuccess() throws URISyntaxException
    {
        List<WorkoutLogThingy> list = new ArrayList<>();
        
        WorkoutLogThingy history = new WorkoutLogThingy();
        history.setAccount(new Account(50));
        history.setExercise(new Exercise());
        history.setSets(3);
        history.setReps(8);
        history.setWeight(155.50);
        history.setDistance(3.10);
        
        list.add(history);
        
        WorkoutHistory history2 = new WorkoutHistory();
        history2.setAccount(new Account(50));
        history2.setExercise(new Exercise());
        history2.setSets(3);
        history2.setReps(8);
        history2.setWeight(155.50);
        history2.setDistance(3.10);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
//        mockRequest.addParameter("id", history.getId().toString());
        mockRequest.addParameter("exercise", history.getExercise().toString());
        mockRequest.addParameter("sets", "" + history.getSets());
        mockRequest.addParameter("reps", "" + history.getReps());
        mockRequest.addParameter("weight", "" + history.getWeight());
        mockRequest.addParameter("distance", "" + history.getDistance());
//        mockRequest.addParameter("time", "" + history.getTime());
//        mockRequest.addParameter("date", "" + history.getWorkout_date());

        Capture<WorkoutLogThingy> capturedHistory = Capture.<WorkoutLogThingy>newInstance();

        EasyMock.expect(this.histService.insertHistory(EasyMock.capture(capturedHistory))).andReturn(history2);
        EasyMock.replay(this.histService);

        ResponseEntity<Map<String, List<WorkoutHistory>>> historyMap = this.controller.addWorkoutsToHistory(history.getAccount().getId(), list);
        List<WorkoutHistory> retHistory = historyMap.getBody().get("success");

        System.err.println(history2.toString());
        System.err.println(capturedHistory.toString());
        
        assertTrue(historyMap.getBody().containsKey("success"));
        assertTrue(history2.deeplyEquals(retHistory.get(0)));
       // assertTrue(history.deeplyEquals(capturedHistory.getValue()));

        EasyMock.verify(this.histService);
    }
}
