package com.ssa.ironyard.fitness.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    public WorkoutHistory insertHistory(WorkoutHistory history)
    {
        return daoHistory.insert(history);
    }

    @Transactional
    public List<WorkoutHistory> readWorkoutHistoryDetail(Integer id, LocalDate date)
    {
        return daoHistory.readByUserIdDate(id, date);
    }
    
    @Transactional
    public List<WorkoutHistory> insertHistory(List<WorkoutHistory> userHistories)
    {
        List<WorkoutHistory> list = new ArrayList<>();

        for (WorkoutHistory h : userHistories)
            list.add(daoHistory.insert(h));
        return list;
    }

    @Transactional
    public Boolean deleteHistory(WorkoutHistory history)
    {
        return daoHistory.delete(history.getId());
    }
}
