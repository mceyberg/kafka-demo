package com.optum.clm.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerAddressService {

	@KafkaListener(topics = "address-changed")
	void addressChanged(@Payload String msg);
}
