package com.lrh;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GeekingRunner.class)
public class GeekingRunnerAutoconfig {

    @Bean
    @ConditionalOnMissingBean(GeekingRunner.class)
    @ConditionalOnProperty(name ="geeking.enabled",havingValue = "true",matchIfMissing = true)
    public GeekingRunner geekingRunner(){
        return new GeekingRunner();
    }

}
