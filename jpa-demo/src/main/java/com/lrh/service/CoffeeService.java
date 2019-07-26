package com.lrh.service;

import com.lrh.entity.Coffee;
import com.lrh.exception.RollBackException;

public interface CoffeeService {

    void insert(Coffee coffee);

    void saveForRollBackException(Coffee coffee) throws RollBackException;

    void saveThenForRollBack(Coffee coffee) throws RollBackException;

}
