package com.lrh.config;

import com.lrh.service.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestConfig2 {

    @Bean
    public TestBean testBeanZ(){
        return new TestBean("BeanZ");
    }

    @Bean
    public TestBeanAspect2 aspect2(){
        return new TestBeanAspect2();
    }

}
