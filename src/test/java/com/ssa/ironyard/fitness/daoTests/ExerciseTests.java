package com.ssa.ironyard.fitness.daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
import com.ssa.ironyard.fitness.model.Exercise;

public class ExerciseTests {
    
  static String URL = "jdbc:mysql://localhost/fitness?" + "user=root&password=root" + "&useServerPrepStmt=true";
    
    
    ExerciseDAOImpl exerciseDAO;
 
    
    @Before
    public void setup() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(URL);
        exerciseDAO = new ExerciseDAOImpl(mysqlDataSource);
       
        exerciseDAO.clear();
    }
    
    @Test
    public void goalInsert(){
        Exercise e = new Exercise();
        e.setExercise_name("Push-ups");
        e.setEquipment(Exercise.EQUIPMENT.NONE);
        e.setIntensity(Exercise.INTENSITY.MEDIUM);
        e.setRegion(Exercise.REGION.ARMS);
        e = exerciseDAO.insert(e);
        
        assertTrue(e.deeplyEquals(exerciseDAO.read(e.getId())));
    }
    
    @Test
    public void goalReadAll(){
        Exercise e = new Exercise();
        e.setExercise_name("Push-ups");
        e.setEquipment(Exercise.EQUIPMENT.NONE);
        e.setIntensity(Exercise.INTENSITY.MEDIUM);
        e.setRegion(Exercise.REGION.ARMS);
        e = exerciseDAO.insert(e);
        
        Exercise e2 = new Exercise();
        e2.setExercise_name("Push-ups");
        e2.setEquipment(Exercise.EQUIPMENT.NONE);
        e2.setIntensity(Exercise.INTENSITY.MEDIUM);
        e2.setRegion(Exercise.REGION.ARMS);
        e2 = exerciseDAO.insert(e2);
        
        assertEquals(2,exerciseDAO.readAll().size());
    }

}
