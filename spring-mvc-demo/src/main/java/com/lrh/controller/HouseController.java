package com.lrh.controller;

import com.lrh.controller.exception.ValidateException;
import com.lrh.controller.request.NewHouseRequest;
import com.lrh.entity.House;
import com.lrh.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(value = "/all",params = "name",
            consumes ={ MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus()
    public String findSomeBody(@RequestParam String name){
        return "{\"name\":" + name + "}";
    }

    @GetMapping(value = "/house/create")
    public String createHouse(@Valid NewHouseRequest houseRequest, BindingResult bindingResult) throws InterruptedException {
        if(bindingResult.hasErrors()){
            throw new ValidateException(bindingResult);
        }
        Thread.sleep(200);
        return "建造成功";
    }
}
