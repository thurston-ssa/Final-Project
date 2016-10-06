package com.ssa.ironyard.fitness.dao;

import java.util.List;

import com.ssa.ironyard.fitness.model.WorkoutHistory;

public interface WorkoutHistoryDAO extends DAO<WorkoutHistory>{

    List<WorkoutHistory> readByUserId(Integer id);

}
