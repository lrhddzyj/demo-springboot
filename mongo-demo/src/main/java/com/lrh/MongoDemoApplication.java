package com.lrh;

import com.lrh.convertor.MoneyReadConvertor;
import com.lrh.model.Coffee;
import com.lrh.repository.CoffeeRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.where;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

@SpringBootApplication
@Slf4j
public class MongoDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongoDemoApplication.class, args);
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions(){
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConvertor()));
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CoffeeRepository coffeeRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Coffee moca = Coffee.builder()
				.name("moca")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();

		mongoTemplate.save(moca);

		log.info("moca info {}", moca);


		Query query = Query.query(Criteria.where("name").is("moca"));
		List<Coffee> coffees = mongoTemplate.find(query, Coffee.class);
		log.info("coffee list is {}", coffees);

		List<Coffee> mocaList = coffeeRepository.findByName("moca");

		log.info("mocaList list is {}", mocaList);

		Thread.sleep(2000);

		UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("moca")),
				new Update()
						.set("price", Money.of(CurrencyUnit.of("CNY"), 30.0))
						.currentDate("updateTime"),
				Coffee.class);

		log.info("update result {}", updateResult.getModifiedCount());

		Coffee updateCoffee = mongoTemplate.findById(moca.getId(), Coffee.class);

		log.info("update result {}", updateCoffee);

		mongoTemplate.remove(updateCoffee);
	}
}
