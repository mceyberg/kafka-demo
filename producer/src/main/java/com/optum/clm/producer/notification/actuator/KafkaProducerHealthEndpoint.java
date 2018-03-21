package com.optum.clm.producer.notification.actuator;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Not yet implemented (correctly) - Spring 2.0.0 changed how Health endpoints were built, and I haven't had
 * enough time to research and implement this feature.
 */
@Slf4j
@EndpointWebExtension(endpoint = HealthEndpoint.class)
public class KafkaProducerHealthEndpoint {

	private final static Long TIMEOUT = TimeUnit.SECONDS.toMillis(3);

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapAddress;

	@Autowired
	private HealthEndpoint delegate;

	private SocketAddress socketAddress;

	@PostConstruct
	public void init() {
		String host = bootstrapAddress.split(":")[0];
		int port = Integer.valueOf(bootstrapAddress.split(":")[1]);
		socketAddress = new InetSocketAddress(host, port);
	}

	@ReadOperation
	public WebEndpointResponse<Health> getHealth() {
		Health health = this.delegate.health();

		Integer status;
		try(Socket socket = new Socket()) {
			socket.connect(socketAddress, TIMEOUT.intValue());
			status = 200;
		} catch (final Exception e) {
			status = 500;
		}

		return new WebEndpointResponse<>(health, status);
	}
}
