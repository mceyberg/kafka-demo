package com.optum.clm.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumerConfig {

	@Value("${producer.root.uri}")
	private String producerRootUri;

	@Bean
	public RestTemplate producerRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.rootUri(producerRootUri)
				.build();
	}
}
