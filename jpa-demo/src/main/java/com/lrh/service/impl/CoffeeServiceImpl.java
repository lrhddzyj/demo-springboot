package com.lrh.service.impl;

import com.lrh.entity.Coffee;
import com.lrh.exception.RollBackException;
import com.lrh.repository.CoffeeRepository;
import com.lrh.service.CoffeeService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CoffeeServiceImpl implements CoffeeService {


    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    @Transactional
    public void insert(Coffee coffee) {
        coffeeRepository.save(coffee);
    }

    @Override
    @Transactional(rollbackOn = RollBackException.class)
    public void saveForRollBackException(Coffee coffee) throws RollBackException {
        coffeeRepository.save(coffee);
        throw  new RollBackException("");
    }

    @Override
    public void saveThenForRollBack(Coffee coffee)throws RollBackException {
//        saveForRollBackException(coffee);

        CoffeeService coffeeService = (CoffeeService) AopContext.currentProxy();
        coffeeService.saveForRollBackException(coffee);

    }
}
