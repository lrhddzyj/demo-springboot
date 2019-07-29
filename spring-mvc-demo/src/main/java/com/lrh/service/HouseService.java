package com.lrh.service;
import java.util.ArrayList;

import com.lrh.entity.House;
import com.lrh.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Transactional
    public House saveHouse(House house){
        return houseRepository.save(house);
    }

    public List<House> findAll(){
        return houseRepository.findAll();
    }

    public List<House> findAll(String name){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("landlord", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Example<House> example = Example.of(House.builder().landlord(name).build(), exampleMatcher);
        return  houseRepository.findAll(example);
    }
}
