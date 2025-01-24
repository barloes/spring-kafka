package org.example.util;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

//https://medium.com/@agvillamizar/implementing-custom-serdes-for-java-objects-using-json-serializer-and-deserializer-in-kafka-streams-d794b66e7c03
@RequiredArgsConstructor
public class JsonSerializer<T> implements Serializer<T> {
	private final Gson gson = new GsonBuilder().create();

	@Override
	public void configure(Map<String, ?> props, boolean isKey) {
		// nothing to do
	}

	@Override
	public byte[] serialize(String topic, T data) {
		if (data == null)
			return null;

		try {
			return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new SerializationException("Error serializing JSON message", e);
		}
	}

	@Override
	public void close() {
		// nothing to do
	}
}
