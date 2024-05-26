package com.appsdeveloperblog.ws.products.config;

import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic createTopic() {
		return TopicBuilder.name("product-creation-event-topic")
				.partitions(2)
				.replicas(2)
				.configs(Map.of("min.insync.replicas","1"))
				.build();
	}
}
