package com.ssa.ironyard.fitness.dao;

import java.sql.ResultSet;

import com.ssa.ironyard.fitness.model.Regimen;

public interface RegimenORM extends ORM<Regimen> {
    @Override
    default String projection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default String table() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default Regimen map(ResultSet results) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default String prepareInsert() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default String prepareUpdate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default String prepareRead() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default String prepareDelete() {
        // TODO Auto-generated method stub
        return null;
    };
}
