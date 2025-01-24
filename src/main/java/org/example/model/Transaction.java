package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.util.Status;

@Builder
@Data
@AllArgsConstructor
public class Transaction {
	String transactionId;
	String fromUserId;
	String toUserId;
	Double amount;

	public FromTransaction getNewFrom() {
		return FromTransaction.builder()
				.transactionId(transactionId)
				.fromUserId(fromUserId)
				.amount(amount)
        .status(Status.PENDING)
        .build();
	}

	public ToTransaction getNewTo() {
		return ToTransaction.builder()
				.transactionId(transactionId)
				.toUserId(toUserId)
				.amount(amount)
        .status(Status.PENDING)
				.build();
	}

	@Data
	@Builder
	public static class FromTransaction {
		String transactionId;
		String fromUserId;
		Double amount;
		Status status;
	}

	@Data
	@Builder
	public static class ToTransaction {
		String transactionId;
		String toUserId;
		Double amount;
		Status status;
	}
}
