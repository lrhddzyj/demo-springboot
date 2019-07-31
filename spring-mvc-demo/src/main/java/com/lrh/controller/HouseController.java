package com.lrh.controller;

import com.lrh.entity.House;
import com.lrh.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public House  save(@RequestParam String name){
        House house = House.builder()
                .name("别墅")
                .landlord(name)
                .roomNum("503")
                .build();
        return  houseService.saveHouse(house);
    }

    @GetMapping(value = "/all",params = "!name")
    public List<House> findAll(){
        return houseService.findAll();
    }

    @GetMapping(value = "/all",params = "name")
    public List<House> findSome(@RequestParam String name){
        return houseService.findAll(name);
    }
}
