package com.lrh;

import com.lrh.converter.BytesToMoneyConverter;
import com.lrh.converter.MoneyToBytesConverter;
import com.lrh.entity.Tea;
import com.lrh.repository.TeaRepository;
import com.lrh.service.TeaServiice;
import io.lettuce.core.ReadFrom;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;

@SpringBootApplication
public class RedisLettuceRepositoryDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisLettuceRepositoryDemoApplication.class, args);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions(){
        return new RedisCustomConversions(Arrays.asList(new BytesToMoneyConverter(), new MoneyToBytesConverter()));
    }

    @Autowired
    private TeaServiice teaServiice;

    @Autowired
    private TeaRepository teaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Tea tea = Tea.builder().name("ab")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.5))
                .build();

        teaRepository.save(tea);

        teaServiice.findByName("ab");

        for (int i = 0; i < 5; i++) {
            teaServiice.findByName("ab");
        }

    }
}
