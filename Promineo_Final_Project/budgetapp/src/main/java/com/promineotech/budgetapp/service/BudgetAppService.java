package com.promineotech.budgetapp.service;

import java.util.List;
import java.util.Map;

import com.promineotech.budgetapp.dto.BudgetBookData;
import com.promineotech.budgetapp.dto.TransactionData;
import com.promineotech.budgetapp.dto.UserData;
import com.promineotech.budgetapp.entity.BudgetBook;
import com.promineotech.budgetapp.entity.Transaction;

public interface BudgetAppService {
	// CRUD Methods for User
	public List<UserData> getAllUsers();
	public UserData getUserById(Long userId);
	public UserData saveUser(UserData userData);
	public Map<String, String> deleteUser(Long userId);
	// CRUD Methods for Budget Book
	public List<BudgetBookData> getAllBugetBooks();
	public BudgetBook findBudgetBookById(Long budgetBookId);
	public BudgetBookData getBudgetBookById(Long budgetBookId);
	public BudgetBookData saveBudgetBook(Long userId, BudgetBookData budgetBookData);
	public Map<String, String> deleteBudgetBook(Long budgetBookId);
	// CRUD Methods for Transaction
	public List<TransactionData> getAllTransactions(Long budgetBookId);
	public TransactionData getTransactionById(Long budgetBookId, Long transactionId);
	public TransactionData saveTransaction(Long budgetBookId, TransactionData transactionData);
	public Map<String, String> deleteTransaction(Long transactionId);
	public void calculateBookTotal(Long budgetBookId, Transaction transaction);
	public List<TransactionData> getAllUserTransactions(Long userId);
}
