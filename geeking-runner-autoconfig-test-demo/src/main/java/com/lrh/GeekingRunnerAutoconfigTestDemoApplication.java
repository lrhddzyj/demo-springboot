package com.lrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekingRunnerAutoconfigTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekingRunnerAutoconfigTestDemoApplication.class, args);
    }

//    @Bean
//    public GeekingRunner geekingRunner(){
//        return new GeekingRunner("spring boot");
//    }


}
