package com.ssa.ironyard.fitness.daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.dao.GoalDAOImpl;
import com.ssa.ironyard.fitness.model.Goal;

public class GoalTests {

  static String URL = "jdbc:mysql://localhost/fitness?" + "user=root&password=root" + "&useServerPrepStmt=true";
    
    
    AccountDAOimpl accountDAO;
    GoalDAOImpl goalDAO;
    
    @Before
    public void setup() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(URL);
        accountDAO = new AccountDAOimpl(mysqlDataSource);
        goalDAO = new GoalDAOImpl(mysqlDataSource);
       
      
        accountDAO.clear();
        goalDAO.clear();
    }
    
    @Test
    public void goalInsert(){
        Goal g = new Goal();
        g.setType(Goal.Type.Endurance);
        g = goalDAO.insert(g);
        
        assertTrue(g.deeplyEquals(goalDAO.read(g.getId())));
    }
}
