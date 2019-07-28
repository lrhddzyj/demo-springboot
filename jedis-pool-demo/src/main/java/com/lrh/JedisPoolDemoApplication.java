package com.lrh;

import com.lrh.entity.Coffee;
import com.lrh.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class JedisPoolDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JedisPoolDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties( "redis")
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host){
        return new JedisPool(jedisPoolConfig(), host);
    }


    @Autowired
    private  JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private CoffeeRepository coffeeRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(jedisPoolConfig.toString());
        Coffee coffee = Coffee.builder()
                .name("moco")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(coffee);

        List<Coffee> all = coffeeRepository.findAll();
//        all.forEach(c ->{
//            jedisPool.
//        });

        try(Jedis jedis = jedisPool.getResource()){
            all.forEach(c ->{
                jedis.hset("spring-bucks", c.getName(), Long.toString(c.getPrice().getAmountMinorLong()));
            });

            Map<String, String> menus = jedis.hgetAll("spring-bucks");
            log.info("Menu: {}", menus);

            String priceLong = jedis.hget("spring-bucks", "moco");
            log.info("moco:{}", Money.of(CurrencyUnit.of("CNY"), Long.valueOf(priceLong)));
        }


    }
}
