package com.lrh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DuridDemoApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(DuridDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        new TestVo().builder()
        TestVo testVo = TestVo.builder().fieldOne("a").fieldTwo("b").build();

        jdbcTemplate.execute("select   from dual");
    }
}
