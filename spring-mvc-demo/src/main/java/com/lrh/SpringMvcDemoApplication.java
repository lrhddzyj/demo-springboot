package com.lrh;

import com.lrh.config.ApplicationContextHandler;
import com.lrh.config.TestConfig;
import com.lrh.config.TestConfig2;
import com.lrh.service.TestBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringMvcDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext applicationContext = ApplicationContextHandler.getContext();

        TestBean AbeanX = (TestBean)applicationContext.getBean("testBeanX");
        AbeanX.hello();


        ApplicationContext fooContext = new AnnotationConfigApplicationContext(TestConfig.class);
        ApplicationContext fooContext2 = new AnnotationConfigApplicationContext(TestConfig2.class);

        TestBean beanX = (TestBean)fooContext.getBean("testBeanX");
        beanX.hello();

        TestBean beanY = (TestBean)fooContext.getBean("testBeanY");
        beanY.hello();

        TestBean beanZ = (TestBean)fooContext2.getBean("testBeanZ");
        beanZ.hello();
    }
}
