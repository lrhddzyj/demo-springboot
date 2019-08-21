package com.lrh.restremplatedemo;

import com.lrh.restremplatedemo.model.House;
import com.lrh.restremplatedemo.model.MyTest;
import com.lrh.restremplatedemo.support.CustomerConnectionKeepAliveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	 *  默认用SimpleClientHttpRequestFactory 生成 restTemplate
	 * @param restTemplateBuilder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}

	@Autowired
	private RestTemplate restTemplate;


	/**
	 * 采用 HttpComponentsClientHttpRequestFactory
	 *
	 * 还可以采用 OkHttp3ClientHttpRequestFactory
	 *
	 * Netty4ClientHttpRequestFactory  已经过时
	 *
	 * @return
	 */
	@Bean
	public HttpComponentsClientHttpRequestFactory httpRequestFactory(){
		PoolingHttpClientConnectionManager connectionManager =
				new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
		connectionManager.setMaxTotal(200);
		connectionManager.setDefaultMaxPerRoute(20);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.evictIdleConnections(30, TimeUnit.SECONDS)
				.disableAutomaticRetries()
				// 有 Keep-Alive 认里面的值，没有的话永久有效
				//.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
				// 换成自定义的
				.setKeepAliveStrategy(new CustomerConnectionKeepAliveStrategy())
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpClient);
		return requestFactory;
	}

	@Bean("customerRestTemplate")
	public RestTemplate restTemplateOfCustomerFactory(RestTemplateBuilder builder){
		return builder
				.setConnectTimeout(Duration.ofMillis(100))
				.setReadTimeout(Duration.ofMillis(500))
				.requestFactory(this::httpRequestFactory)
				.build();
	}


	@Resource
	private RestTemplate customerRestTemplate;



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
	 * 解析泛型对象
	 */
	private void parameterizedType(){
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/all").build("");
		RequestEntity requestEntity = RequestEntity.get(uri)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		//接受泛型对象 直接使用泛型对象会报错！！！
		ParameterizedTypeReference<List<House>> pr = new ParameterizedTypeReference<List<House>>() {};

		ResponseEntity<List<House>> exchange = customerRestTemplate.exchange(requestEntity, pr);
		log.info("exchange responseStatus {} ; response Headers {}", exchange.getStatusCode(), exchange.getHeaders());
		log.info("exchange responseBody {}",exchange.getBody());
	}

}
