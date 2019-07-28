package com.lrh;

import com.lrh.entity.Tea;
import com.lrh.service.TeaServicie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@Slf4j
public class SpringCacheDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheDemoApplication.class, args);
    }


    @Autowired
    private TeaServicie teaServicie;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tea tea = Tea.builder()
                .name("baicha")
                .weight(20)
                .build();

        teaServicie.save(tea);
        log.info(tea.toString());

        List<Tea> all = teaServicie.findAll();
        log.info("all size {}", all.size());

        for (int i = 0; i < 5 ; i++) {
            log.info("load data from cache:");
            teaServicie.findAll();
        }

        teaServicie.reloadTea();

        teaServicie.findAll();


    }
}
