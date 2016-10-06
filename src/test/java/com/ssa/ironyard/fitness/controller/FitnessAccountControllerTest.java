package com.ssa.ironyard.fitness.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Map;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.Password;
import com.ssa.ironyard.fitness.services.FitnessAccountServiceImpl;
import com.ssa.ironyard.fitness.services.FitnessHistoryServiceImpl;

public class FitnessAccountControllerTest
{

    FitnessAccountServiceImpl accService;
    FitnessHistoryServiceImpl histService;
    FitnessAccountController controller;

    Account a;
    
    @Before
    public void mock()
    {
        this.accService = EasyMock.createNiceMock(FitnessAccountServiceImpl.class);
        this.histService = EasyMock.createNiceMock(FitnessHistoryServiceImpl.class);

        this.controller = new FitnessAccountController(accService, histService);
        
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
    public void updateTest() throws URISyntaxException
    {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addParameter("firstName", "David");
        mockRequest.addParameter("lastName", "Shea");
        mockRequest.addParameter("age", "50");
        mockRequest.addParameter("gender", "Female");
        mockRequest.addParameter("goal", "Endurance");
        mockRequest.addParameter("weight", "55");
        mockRequest.addParameter("height", "5.5");
        
        Capture<Account> capturedAcc = Capture.<Account>newInstance();

        EasyMock.expect(this.accService.updateAccount(EasyMock.capture(capturedAcc))).andReturn(a);
        EasyMock.replay(this.accService);
        

        ResponseEntity<Map<String,Account>> accountMap = this.controller.updateAccount(a.getId(), mockRequest);
        Account retAccount = accountMap.getBody().get("success");
        
        assertEquals(capturedAcc.getValue().getId(), retAccount.getId());
        assertEquals(capturedAcc.getValue().getUsername(), retAccount.getUsername()); // null == null
        assertEquals(capturedAcc.getValue().getFirstName(), retAccount.getFirstName());
        assertEquals(capturedAcc.getValue().getAge(), retAccount.getAge());

        System.err.println(capturedAcc.getValue().getUsername());
        System.err.println(retAccount.getId());

        EasyMock.verify(this.accService);
    }

}
