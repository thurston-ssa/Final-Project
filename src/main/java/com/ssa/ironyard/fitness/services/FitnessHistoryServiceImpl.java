package com.ssa.ironyard.fitness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAO;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.WorkoutHistory;
import com.ssa.ironyard.fitness.model.WorkoutLogThingy;

@Component
public class FitnessHistoryServiceImpl
{

    WorkoutHistoryDAO daoHistory;

    @Autowired
    public FitnessHistoryServiceImpl(WorkoutHistoryDAOImpl daoHistory)
    {
        this.daoHistory = daoHistory;
    }
    
    @Transactional
    public List<WorkoutHistory> readAll(Integer id)
    {
        return daoHistory.readByUserId(id);
    }

    @Transactional
    public WorkoutHistory insertHistory(WorkoutLogThingy log)
    {
        WorkoutHistory history = new WorkoutHistory();
        history.setAccount(new Account(log.getAccount().getId()));
        history.setExercise(log.getExercise());
        history.setSets(log.getSets());
        history.setReps(log.getReps());
        history.setWeight(log.getWeight());
//        history.setTime(log.getTime());
//        history.setWorkout_date(log.getWorkout_date());
//        history.setDistance(log.getDistance());
        
        return daoHistory.insert(history);
    }
    
    @Transactional
    public Boolean deleteHistory(WorkoutHistory history)
    {
        return daoHistory.delete(history.getId());
    }
}
