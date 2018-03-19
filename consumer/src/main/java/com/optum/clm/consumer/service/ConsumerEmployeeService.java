package com.optum.clm.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerEmployeeService {

	@KafkaListener(topics = "employee-changed")
	void employeeChanged(@Payload String msg);
}
