package org.example.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
//https://www.baeldung.com/spring-boot-kafka-streams
public class KafkaConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	KafkaStreamsConfiguration kStreamsConfig() {
		return new KafkaStreamsConfiguration(getConfiguration());
	}

	Map<String, Object> getConfiguration() {
		Map<String, Object> props = new HashMap<>();

		// Give the Streams application a unique name.  The name must be unique in the Kafka cluster
		// against which the application is run.
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-lambda-example");
		props.put(StreamsConfig.CLIENT_ID_CONFIG, "wordcount-lambda-example-client");
		// Where to find Kafka broker(s).
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		// Specify default (de)serializers for record keys and for record values.
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
		// Records should be flushed every 10 seconds. This is less than the default
		// in order to keep this example interactive.
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		// For illustrative purposes we disable record caches.
		props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);
		// Use a temporary directory for storing state, which will be automatically removed after the test.
		return props;
	}
}