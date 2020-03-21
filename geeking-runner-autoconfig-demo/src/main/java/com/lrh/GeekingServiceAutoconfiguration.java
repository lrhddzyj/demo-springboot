package com.lrh;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GeekingService.class)
public class GeekingServiceAutoconfiguration {

    @Bean
    @ConditionalOnMissingBean(GeekingService.class)
    @ConditionalOnProperty(name = "geeking.enable",havingValue = "true",matchIfMissing = true)
    public GeekingService geekingService(){
        return new GeekingService("default name");
    }


}
