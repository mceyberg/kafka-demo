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

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Async
	@Override
	public CompletableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message) {


		ListenableFuture<SendResult<String, String>> test = kafkaTemplate.send(topic, key, message);
		return test.completable();
	}
}
