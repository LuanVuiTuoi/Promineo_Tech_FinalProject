package com.promineotech.budgetapp.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
		private Long transactionId;
		private String transactionName;
		private String transactionType;
		private String transactionCategory;
		private String transactionDate;
		private BigDecimal transactionAmount;
	
}
