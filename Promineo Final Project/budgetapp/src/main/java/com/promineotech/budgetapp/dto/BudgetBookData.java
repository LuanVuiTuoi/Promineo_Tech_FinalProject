package com.promineotech.budgetapp.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetBookData {
	private Long budgetBookId;
	private String bookName;
	private BigDecimal bookTotalExpense;
	private BigDecimal bookTotalIncome;
	private String bookCreatedDate;	
	private Set<TransactionData> transactions = new HashSet<TransactionData>();
	private Set<UserData> users = new HashSet<UserData>();
}
