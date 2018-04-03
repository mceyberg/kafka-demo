package com.optum.clm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.optum.clm.consumer.model.Address;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerAddressServiceImpl implements ConsumerAddressService {

	private static final String FIND_ADDRESS_BY_ID_ENDPOINT = "/addresses/{addressId}";

	private final RestTemplate producerRestTemplate;

	@Autowired
	public ConsumerAddressServiceImpl(RestTemplate producerRestTemplate) {
		this.producerRestTemplate = producerRestTemplate;
	}

	@Override
	public void addressChanged(@Payload String msg) {

		log.info("Kafka message received, message: {}", msg);
		Integer addressId = Integer.parseInt(msg);

		Address address = producerRestTemplate.getForEntity(FIND_ADDRESS_BY_ID_ENDPOINT, Address.class, addressId).getBody();

		log.info("Address data: {}", address);
	}
}
