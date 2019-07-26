package com.lrh;

import com.lrh.common.OrderState;
import com.lrh.entity.Coffee;
import com.lrh.entity.CoffeeOrder;
import com.lrh.exception.RollBackException;
import com.lrh.repository.CoffeeOrderRepository;
import com.lrh.repository.CoffeeRepository;
import com.lrh.repository.NewCoffeeRepository;
import com.lrh.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableAspectJAutoProxy(exposeProxy = true)
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private NewCoffeeRepository newCoffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
//        testTransactionDemo1();
//        testTransactionDemo2();

        initData();

        selectData();

    }

    public void  initData(){
        Coffee coffee = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 10.20))
                .build();
        coffeeRepository.save(coffee);

       Coffee moca = Coffee.builder().name("moca")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(moca);

        CoffeeOrder order = CoffeeOrder.builder().customer("Li Lei")
                .items(Collections.singletonList(coffee))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);

        order = CoffeeOrder.builder().customer("Li Lei")
                .items(Collections.singletonList(moca))
                .state(OrderState.INIT)
                .build();

        coffeeOrderRepository.save(order);
    }

    public void selectData(){
        newCoffeeRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .forEach(coffee -> log.info("Load All {}",coffee));

        log.info("------------------分割线---------------------");


        log.info("lasted string = {}", coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc()
                .stream()
                .map(CoffeeOrder::getCustomer)
                .reduce(",", StringUtils::join));

        log.info("------------------分割线---------------------");

        coffeeOrderRepository.findByCustomer("Li lei")
                .stream()
                .forEach(coffeeOrder ->{
                    log.info("order id :{}", coffeeOrder.getId());
                    coffeeOrder.getItems().forEach(item -> log.info("{}", item));
                });

        log.info("------------------分割线---------------------");

        coffeeOrderRepository.findByItems_Name("latte").forEach(o -> {
            log.info("order {}", o);
        });

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> exampleCoffee = newCoffeeRepository.findOne(Example.of(Coffee.builder().name("moca").build(), exampleMatcher));
        log.info("exampleCoffee {}", exampleCoffee);
    }



    /**
     * 编程式事务管理
     */
    public void testTransactionDemo2(){
        log.info("count before transaction : {}", getCount());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Coffee aa = Coffee.builder().name("AA").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
                coffeeRepository.save(aa);
                log.info("coiunt in transaction :{}", getCount());
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("coiunt after transaction :{}", getCount());
    }



    public long getCount(){
        return coffeeRepository.count();
    }


    /**
     * 声明式事务管理
     */
    public void testTransactionDemo1(){
        Coffee aa = Coffee.builder().name("AA").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        coffeeService.insert(aa);
        log.info("AA={}",coffeeRepository.findByName("AA"));

        Coffee bb = Coffee.builder().name("BB").price(Money.of(CurrencyUnit.of("CNY"), 22.0)).build();
        try {
            coffeeService.saveForRollBackException(bb);
        } catch (RollBackException e) {
            log.info("BB={}",coffeeRepository.findByName("BB"));
        }
        Coffee CCC = Coffee.builder().name("CCC").price(Money.of(CurrencyUnit.of("CNY"), 32.0)).build();
        try {
            coffeeService.saveThenForRollBack(CCC);
        } catch (RollBackException e) {
            log.info("CCC={}",coffeeRepository.findByName("CCC"));
        }
    }
}
