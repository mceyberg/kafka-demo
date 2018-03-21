package com.optum.clm.producer.notification.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;

/**
 *
 */
public interface NotificationService {

	CompletableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message);
}
