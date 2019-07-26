package com.lrh;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @Transactional(propagation = Propagation.NESTED,isolation = Isolation.DEFAULT)
    public void test() {
        ((TestService) (AopContext.currentProxy())).test2();
    }

    @Override
    public void test2() {

    }
}
