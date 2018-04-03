package com.optum.clm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.optum.clm.consumer.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerEmployeeServiceImpl implements ConsumerEmployeeService {

	private static final String FIND_EMPLOYEE_BY_ID_ENDPOINT = "/employees/{employeeId}";

	private final RestTemplate producerRestTemplate;

	@Autowired
	public ConsumerEmployeeServiceImpl(RestTemplate producerRestTemplate) {
		this.producerRestTemplate = producerRestTemplate;
	}

	@Override
	public void employeeChanged(@Payload String msg) {
		log.info("Kafka message received, message: {}", msg);

		Integer employeeId = Integer.parseInt(msg);

		Employee employee = producerRestTemplate.getForEntity(FIND_EMPLOYEE_BY_ID_ENDPOINT, Employee.class, employeeId).getBody();

		log.info("Employee data: {}", employee);
	}
}
