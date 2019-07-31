package com.lrh.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Data
@Slf4j
public class TestBean {

    private String msg;

    public void hello(){
        log.info(msg);
    }
}
