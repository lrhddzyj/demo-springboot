package com.lrh;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import com.lrh.mapper.HouseMapper;
import com.lrh.mapper.HousePlusMapper;
import com.lrh.model.House;
import com.lrh.model.HouseExample;
import com.lrh.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.lrh.mapper")
@Slf4j
public class MybatisGeneratorDemo implements CommandLineRunner {

//    @Autowired
//    private HouseService houseService;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HousePlusMapper housePlusMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        House house = new House().withHouseType("100")
                .withCity("BJ")
                .withMobile("18888888889")
                .withOwner("abc");
//        House house =   House.builder().roomNum("100").build();
//        for (int i = 0; i < 5 ; i++) {
//            house.setFloor(i);
//            houseMapper.insert(house);
//        }

        HouseExample houseExample = new HouseExample();
        houseExample.createCriteria()
                .andMobileEqualTo("18888888889");
//        houseMapper.selectByExample(houseExample);

        houseMapper.selectByExampleWithRowbounds(houseExample, new RowBounds(10,0));

//        housePlusMapper.selectByExampleWithPageRowbounds(new PageRowBounds(1, 3));
        housePlusMapper.selectByExampleWithPageRowbounds(new PageRowBounds(1, 0));
        housePlusMapper.selectByRowbounds(new RowBounds(1, 0));
        List<House> houses = housePlusMapper.selectWithParam(1, 3);
        PageInfo pageInfo = new PageInfo(houses);

        log.info(pageInfo.toString());

    }
}
