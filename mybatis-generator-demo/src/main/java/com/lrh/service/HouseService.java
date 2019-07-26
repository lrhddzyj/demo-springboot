package com.lrh.service;

import com.lrh.mapper.HouseMapper;
import com.lrh.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HouseService{

    @Autowired
    private HouseMapper houseMapper;



    public House selectByPrimaryKey(Long id) {
        return houseMapper.selectByPrimaryKey(id);
    }

//

}
