package com.ssa.ironyard.fitness.dao;

import com.ssa.ironyard.fitness.model.Account;

public interface AccountDAO {

    Account readByUsername(String username);

    Account eagerRead(Integer id);

}
