package org.example.util;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.model.Transaction;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@RequiredArgsConstructor
public final class CustomSerdes {
	public static Serde<Transaction> Transaction() {
		JsonSerializer<Transaction> serializer = new JsonSerializer<>();
		JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}
}
