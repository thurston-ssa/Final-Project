package com.ssa.ironyard.fitness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
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
    
    @Transactional
    public List<Exercise> readAllExercises()
    {
        return daoExercise.readAll();
    }
    
    @Transactional
    public Exercise readExercise(Integer id)
    {
        return daoExercise.read(id);
    }

}
