package com.ssa.ironyard.fitness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;

@Component
public class FitnessExerciseServiceImpl
{

    ExerciseDAOImpl daoExercise;

    @Autowired
    public FitnessExerciseServiceImpl(ExerciseDAOImpl daoExercise)
    {
        this.daoExercise = daoExercise;
    }

    public List<Exercise> readAllExercises()
    {
        return daoExercise.readAll();
    }
    
    public Exercise readExercise(Integer id)
    {
        return daoExercise.read(id);
    }

}
