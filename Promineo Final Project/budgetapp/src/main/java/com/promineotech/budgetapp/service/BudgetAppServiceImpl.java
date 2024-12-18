package com.promineotech.budgetapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.budgetapp.dto.BudgetBookData;
import com.promineotech.budgetapp.dto.TransactionData;
import com.promineotech.budgetapp.dto.UserData;
import com.promineotech.budgetapp.entity.BudgetBook;
import com.promineotech.budgetapp.entity.Transaction;
import com.promineotech.budgetapp.entity.User;
import com.promineotech.budgetapp.repository.BudgetBookRepository;
import com.promineotech.budgetapp.repository.TransactionRepository;
import com.promineotech.budgetapp.repository.UserRepository;

@Service
public class BudgetAppServiceImpl implements BudgetAppService {
	
	private final BudgetBookRepository budgetBookRepo;
	private final UserRepository userRepo;
	private final TransactionRepository transactionRepo;
	private final ModelMapper mapper;
	
	@Autowired
	public BudgetAppServiceImpl (BudgetBookRepository budgetBookRepo, UserRepository userRepo, TransactionRepository transactionRepo, ModelMapper mapper) {
		this.budgetBookRepo = budgetBookRepo;
		this.userRepo = userRepo;
		this.transactionRepo = transactionRepo;
		this.mapper = mapper;
	}
	/**
	 * Return all users in the database.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserData> getAllUsers() {
		List<UserData> userList = userRepo
										.findAll()
										.stream()
										.map(user -> mapper.map(user, UserData.class))
										.collect(Collectors.toList());
		return userList;
	}
	/**
	 * @param Long userId
	 * Check that userId is not null before returning the user. 
	 */
	@Override
	@Transactional(readOnly = true)
	public UserData getUserById(Long userId) {
		if(Objects.isNull(userId)) {
			throw new NoSuchElementException("User Id: " + userId + " was not found.");
		}
		
		User user = userRepo
				.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User with Id: " + userId + " could not be retrieved."));
		
		return mapper.map(user, UserData.class);
	}
	/**
	 * @param UserData userData
	 * Check that userData contains a userId.
	 * If userId exists update the user otherwise create a new user.
	 * Return the user info using UserDataDTO.
	 */
	@Override
	public UserData saveUser(UserData userData) {
		// Update a user.
		if(Objects.nonNull(userData.getUserId())) {
			return userRepo
					.findById(userData.getUserId())
					.map(user -> {
								user.setFirstName(userData.getFirstName());
								user.setLastName(userData.getLastName());
								user.setEmail(userData.getEmail());
								return mapper.map(userRepo.save(user), UserData.class);
								})
					.orElseThrow(() -> new NoSuchElementException("User with Id: " + userData.getUserId() + " could not be updated."));
					
		}
		// Create a new user.
		User user = mapper.map(userData, User.class);
		return mapper.map(userRepo.save(user), UserData.class);
	}
	/**
	 * @param Long userId
	 * If user id is valid, delete user and return a delete message.
	 */
	@Override
	public Map<String, String> deleteUser(Long userId) {
		// Check that user exist in database.
		if(!userRepo.existsById(userId)) {
			throw new NoSuchElementException("User: " + userId + " was not found in database.");
		}
		userRepo.deleteById(userId);
		return Collections.singletonMap("message", "User with the ID: " + userId + " has been deleted");
	}
	/**
	 * Get all of the budget books.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BudgetBookData> getAllBugetBooks() {
		List<BudgetBookData> budgetBookList = budgetBookRepo
													.findAll()
													.stream()
													.map(book -> mapper.map(book, BudgetBookData.class))
													.collect(Collectors.toList());
		
		return budgetBookList;
	}
	/**
	 * @param Long budgetBookId
	 * Help to check and find that budget book exist in the database.
	 * Return the BudgetBook object.
	 */
	@Override
	public BudgetBook findBudgetBookById(Long budgetBookId) {
		if(Objects.isNull(budgetBookId)) {
			throw new NoSuchElementException("Budget Book Id: " + budgetBookId + " was not found.");
		}
		
		BudgetBook budgetBook = budgetBookRepo
									.findById(budgetBookId)
									.orElseThrow(() -> new NoSuchElementException("Budget Book with Id: " + budgetBookId + " could not be retrieved."));
		return budgetBook;
	}
	/**
	 * Get budget book and map data to dto.
	 */
	@Override
	@Transactional(readOnly = true)
	public BudgetBookData getBudgetBookById(Long budgetBookId) {
		return mapper.map(findBudgetBookById(budgetBookId), BudgetBookData.class);
	}
	
	/**
	 * @param Long userId
	 * @param BudgetBookData budgetBookData
	 * Check that userId is not null;
	 * If a budget book id exists then update the book info or 
	 * create a new budget book and assign the user to it using the user id.
	 */
	@Override
	public BudgetBookData saveBudgetBook(Long userId, BudgetBookData budgetBookData) {
		if(Objects.isNull(userId)) {
			throw new NoSuchElementException("User Id: " + userId + " was not found.");
		}
		// Update a budget book.
		if(Objects.nonNull(budgetBookData.getBudgetBookId())) {
			return budgetBookRepo
					.findById(budgetBookData.getBudgetBookId())
					.map(book -> {
									book.setBookName(budgetBookData.getBookName());
									book.setBookTotalExpense(budgetBookData.getBookTotalExpense());
									book.setBookTotalIncome(budgetBookData.getBookTotalIncome());
									book.setBookCreatedDate(budgetBookData.getBookCreatedDate());
									return mapper.map(budgetBookRepo.save(book), BudgetBookData.class);
								})
					.orElseThrow(() -> new NoSuchElementException("Budget book with Id: " + budgetBookData.getBudgetBookId() + " could not be updated."));
		}
		// Create a new budget book and map it to a user.
		User user = userRepo
				.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User with Id: " + userId + " could not be retrieved."));
		BudgetBook budgetBook = mapper.map(budgetBookData, BudgetBook.class);
		user.getUserBudgetBooks().add(budgetBook);
		budgetBook.getUsers().add(user);
		return mapper.map(budgetBookRepo.save(budgetBook), BudgetBookData.class);
	}
	/**
	 * @param Long budgetBookId
	 * If the book id is valid, delete the book and return delete message.
	 */
	@Override
	public Map<String, String> deleteBudgetBook(Long budgetBookId) {
		// Check that budget book exist in database.
		if(!budgetBookRepo.existsById(budgetBookId)) {
			throw new NoSuchElementException("Budget Book: " + budgetBookId + " was not found in database.");
		}
		budgetBookRepo.deleteById(budgetBookId);
		return Collections.singletonMap("message", "Budget Book with ID: " + budgetBookId + " has been deleted.");
	}
	/**
	 * @param Long budgetBookId
	 * Using the budgetBookId get all of the transaction related.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TransactionData> getAllTransactions(Long budgetBookId) {
		BudgetBookData book = getBudgetBookById(budgetBookId);
		List<TransactionData> transactionList = book
												.getTransactions()
												.stream()
												.map(t -> mapper.map(t, TransactionData.class))
												.collect(Collectors.toList());
		return transactionList;
	}
	/**
	 * @param Long budgetBookId
	 * @param Long transactionId
	 * Get the specific transaction related to the budget book.
	 */
	@Override
	@Transactional(readOnly = true)
	public TransactionData getTransactionById(Long budgetBookId, Long transactionId) {
		if(Objects.isNull(transactionId)) {
			throw new NoSuchElementException("Transaction Id: " + transactionId + " was not found.");
		}
		BudgetBookData book = getBudgetBookById(budgetBookId);
		return book.getTransactions()
				.stream()
				.filter(t -> t.getTransactionId().equals(transactionId))
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("Budget Book with Id: " + transactionId + " could not be retrieved."));
	}
	/**
	 * @param Long budgetBookId
	 * @param TransactionData transactionData
	 * Check budget book id is valid.
	 * Update Transaction if already exists or create a new transaction.
	 * Call calculateBookTotal method to update total amount in budget book.
	 */
	@Override
	public TransactionData saveTransaction(Long budgetBookId, TransactionData transactionData) {
		if(Objects.isNull(budgetBookId)) {
			throw new NoSuchElementException("Budget Book Id: " + budgetBookId + " was not found.");
		}
		
		//Update a transaction.
		if(Objects.nonNull(transactionData.getTransactionId())) {
			return transactionRepo
					.findById(transactionData.getTransactionId())
					.map(t -> {
									t = mapper.map(transactionData, Transaction.class);
									calculateBookTotal(budgetBookId, t);
									return mapper.map(transactionRepo.save(t), TransactionData.class);
								})
					.orElseThrow(() -> new NoSuchElementException("Transaction with Id: " + transactionData.getTransactionId() + " could not be updated."));
		}
		
		//Create a new transaction.
		Transaction transaction = mapper.map(transactionData, Transaction.class);
		BudgetBook book = findBudgetBookById(budgetBookId);
		book.getTransactions().add(transaction);
		transaction.setBudgetBook(book);
		calculateBookTotal(budgetBookId, transaction);
		return mapper.map(transactionRepo.save(transaction), TransactionData.class);
	}

	@Override
	public Map<String, String> deleteTransaction(Long transactionId) {
		// Check that the transaction exist in database.
		if(!transactionRepo.existsById(transactionId)) {
			throw new NoSuchElementException("User: " + transactionId + " was not found in database.");
		}
		
		transactionRepo.deleteById(transactionId);
		return Collections.singletonMap("message", "Budget Book with ID: " + transactionId + " has been deleted.");
	}
	
	/**
	 * @param Long budgetBookId
	 * @param Transaction transaction
	 * Calculate the total expense and income of budget book each time a transaction is added or updated.
	 */
	@Override
	public void calculateBookTotal(Long budgetBookId, Transaction transaction) {	
		BudgetBook book = findBudgetBookById(budgetBookId);
		
		switch(transaction.getTransactionType().toLowerCase()) {
		case "deposit":
			BigDecimal income = book.getBookTotalIncome();
			book.setBookTotalIncome(income.add(transaction.getTransactionAmount()));
			break;
		case "withdraw":
			BigDecimal expense = book.getBookTotalExpense();
			book.setBookTotalExpense(expense.add(transaction.getTransactionAmount()));
			break;
		default:
			throw new NoSuchElementException("The value is incorrect " + transaction.getTransactionType());
		}
		
	}
	@Override
	@Transactional(readOnly = true)
	public List<TransactionData> getAllUserTransactions(Long userId){
		User user = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User with Id: " + userId + " could not be retrieved."));
		
		List<BudgetBookData> userBooks = budgetBookRepo
				.findAll()
				.stream()
				.filter(book -> book.getUsers().contains(user)).map(book -> mapper.map(book, BudgetBookData.class))
				.collect(Collectors.toList());
		
		List<TransactionData> userTransaction = new ArrayList<TransactionData>();
		
		for(BudgetBookData book : userBooks) {
			userTransaction.addAll(book.getTransactions());
		}
		
		return userTransaction;
	}
	


	

	


}
