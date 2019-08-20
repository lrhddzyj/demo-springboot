package com.lrh.restremplatedemo;

import com.lrh.restremplatedemo.model.House;
import com.lrh.restremplatedemo.model.MyTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@Slf4j
public class RestRemplateDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(RestRemplateDemoApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);
//		SpringApplication.run(RestRemplateDemoApplication.class, args);
	}

	/**
	 *
	 * @param restTemplateBuilder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		simpleRestTemplate();

		parameterizedType();

	}


	/**
	 * 简单的restTemplate调用
	 */
	private void simpleRestTemplate(){
//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/all?name={name}")
				.build("test");

		RequestEntity postRequestEntity = RequestEntity.post(uri)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
//		HttpEntity httpEntity = new HttpEntity(headers);
		ResponseEntity<String> exchange = restTemplate.exchange(postRequestEntity, String.class);
//		 restTemplate.getForEntity(uri, MyTest.class);
		log.info("exchange responseStatus {} ; response Headers {}", exchange.getStatusCode(), exchange.getHeaders());
		log.info("exchange responseBody {}",exchange.getBody());
	}

	/**
	 * 接收泛型对象
	 */
	private void parameterizedType(){
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/all").build("");
		RequestEntity requestEntity = RequestEntity.get(uri)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		//接受泛型对象 直接使用泛型对象会报错！！！
		ParameterizedTypeReference<List<House>> pr = new ParameterizedTypeReference<List<House>>() {};

		ResponseEntity<List<House>> exchange = restTemplate.exchange(requestEntity, pr);
		log.info("exchange responseStatus {} ; response Headers {}", exchange.getStatusCode(), exchange.getHeaders());
		log.info("exchange responseBody {}",exchange.getBody());
	}

}
