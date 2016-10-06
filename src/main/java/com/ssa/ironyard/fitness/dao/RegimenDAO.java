package com.ssa.ironyard.fitness.dao;

import java.util.List;

import com.ssa.ironyard.fitness.model.Regimen;

public interface RegimenDAO {

    
    List<Regimen> readByUserId(Integer id);

}
