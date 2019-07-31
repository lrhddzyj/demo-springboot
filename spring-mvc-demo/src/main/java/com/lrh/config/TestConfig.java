package com.lrh.config;

import com.lrh.service.TestBean;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestConfig {

    @Bean
    public TestBean testBeanX(){
        return new TestBean("BeanX");
    }

    @Bean
    public TestBean testBeanY(){
        return new TestBean("BeanY");
    }

     @Bean
    public TestBeanAspect aspect(){
         return new TestBeanAspect();
     }

//    @Pointcut("bean(testBean*)")
//    public void testBean(){
//
//    }


}
