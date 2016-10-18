package com.ssa.ironyard.fitness.model;

import com.ssa.ironyard.fitness.model.Regimen.DAY;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author thurston
 */
public class WorkoutRegimenTests 
{
    @Test
    public void sanity()
    {
        WorkoutRegimen workoutRegimen = new WorkoutRegimen();
        assertNull("", workoutRegimen.getAccount());
        assertFalse("", workoutRegimen.iterator().hasNext());
        for (DAY day : DAY.values())
            assertTrue("", workoutRegimen.daily(day).isEmpty());
    }
    
    @Test
    public void account()
    {
        WorkoutRegimen workoutRegimen = new WorkoutRegimen();
        Account user = new Account(Integer.SIZE);
        
        Regimen exercise = new Regimen();
        exercise.setDay(DAY.Sunday);
        exercise.setExercise(new Exercise((int) 'A'));
        exercise.setAccount(user);
        workoutRegimen.add(exercise);
        assertEquals("", user, workoutRegimen.getAccount());
        
        exercise = new Regimen();
        exercise.setDay(DAY.Sunday);
        exercise.setExercise(new Exercise((int) 'C'));
        exercise.setAccount(user);
        workoutRegimen.add(exercise);
        
        assertEquals("", user, workoutRegimen.getAccount());
    }
}