package com.lrh.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class TestBeanAspect {

    @AfterReturning("bean(testBean*))")
    public void after(){
        log.info("after Return hello");
    }
}
