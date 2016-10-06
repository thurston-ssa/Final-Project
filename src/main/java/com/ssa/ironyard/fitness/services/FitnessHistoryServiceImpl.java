package com.ssa.ironyard.fitness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAO;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.WorkoutHistory;

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
    public WorkoutHistory insertHistory(WorkoutHistory history)
    {
        return daoHistory.insert(history);
    }
    
    @Transactional
    public Boolean deleteHistory(WorkoutHistory history)
    {
        return daoHistory.delete(history.getId());
    }
}
