package com.lrh;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


@Slf4j
public class GeekingRunner implements ApplicationRunner {

    private String name;


    public GeekingRunner() {
        log.info("GeekingRunner init without params");
    }

    public GeekingRunner(String name) {
        this.name = name;
        log.info("GeekingRunner init with params name");
    }

    public void run(ApplicationArguments args) throws Exception {
        if(name == null){
            log.info("hello everyone , we all like spring ");
        }else{
            log.info("hello everyone , we all like  {}", name);
        }

    }
}
