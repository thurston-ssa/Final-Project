package com.ssa.ironyard.fitness.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.ironyard.fitness.dao.RegimenDAOImpl;
import com.ssa.ironyard.fitness.model.Regimen;

@Component
public class FitnessRegimenServiceImpl
{

    RegimenDAOImpl daoRegimen;

    @Autowired
    public FitnessRegimenServiceImpl(RegimenDAOImpl daoRegimen)
    {
        this.daoRegimen = daoRegimen;
    }
    
    @Transactional
    public Regimen readRegimen(Integer id)
    {
        return daoRegimen.read(id);
    }
    
    @Transactional
    public List<Regimen> readAll(Integer id)
    {
        return daoRegimen.readByUserId(id);
    }

    @Transactional
    public Regimen insertRegimen(Regimen regimen)
    {
        return daoRegimen.insert(regimen);
    }
    
    @Transactional
    public Regimen updateRegimen(Regimen regimen)
    {
        return daoRegimen.update(regimen);
    }
    
    @Transactional
    public Boolean deleteRegimen(Integer id)
    {
        return daoRegimen.delete(id);
    }
}