package com.optum.clm.producer.notification.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public NotificationServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Async
	@Override
	public CompletableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message) {


		ListenableFuture<SendResult<String, String>> test = kafkaTemplate.send(topic, key, message);
		return test.completable();
	}
}
