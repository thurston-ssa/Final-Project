package com.ssa.ironyard.fitness.dao;

import java.time.LocalDate;
import java.util.List;

import com.ssa.ironyard.fitness.model.WorkoutHistory;

public interface WorkoutHistoryDAO extends DAO<WorkoutHistory>{

    List<WorkoutHistory> readByUserId(Integer id);
    
    List<WorkoutHistory> readByUserIdDate(Integer id, LocalDate date);


}
