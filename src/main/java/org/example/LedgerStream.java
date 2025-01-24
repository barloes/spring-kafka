//package org.example;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.Consumed;
//import org.apache.kafka.streams.kstream.KStream;
//import org.example.model.Transaction;
//import org.example.util.CustomSerdes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
////@Slf4j
//public class LedgerStream {
//
//	private static final String LEDGER_TOPIC = "stream-ledger";
//	@Autowired
//	void handleTransaction(StreamsBuilder streamsBuilder) {
//		KStream<String, Transaction> messageStream = streamsBuilder.stream(LEDGER_TOPIC, Consumed.with(Serdes.String(), CustomSerdes.Transaction()));
//
//		KStream<String, Transaction.FromTransaction> fromTransactionStream = messageStream.mapValues(transaction -> {
//		return transaction.getNewFrom(); // Extract the "from" part
//		});
//
//		// Create a stream for ToTransaction
//		KStream<String, Transaction.ToTransaction> toTransactionStream = messageStream.mapValues(transaction -> {
//		return transaction.getNewTo(); // Extract the "to" part
//		});
//
//		// add to streams
//		fromTransactionStream.to("stream-from-transaction");
//		toTransactionStream.to("stream-to-transaction");
//	}
//}
