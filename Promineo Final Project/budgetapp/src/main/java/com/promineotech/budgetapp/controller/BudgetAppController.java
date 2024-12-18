package com.promineotech.budgetapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.budgetapp.dto.BudgetBookData;
import com.promineotech.budgetapp.dto.TransactionData;
import com.promineotech.budgetapp.dto.UserData;
import com.promineotech.budgetapp.service.BudgetAppService;

@RestController
@RequestMapping("/budget_app/")
public class BudgetAppController {
	private BudgetAppService budgetAppService;
	
	@Autowired
	public BudgetAppController(BudgetAppService budgetAppService) {
		this.budgetAppService = budgetAppService;
	}
	
	@GetMapping("/user/all")
	@ResponseBody
	public List<UserData> getAllUsers(){
		return budgetAppService.getAllUsers();
	}
	
	@GetMapping("/user/{user_id}")
	@ResponseBody
	public UserData getUserById(@RequestParam Long userId) {
		return budgetAppService.getUserById(userId);
	}
	
	@PostMapping("/user/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserData saveUser(@RequestBody UserData userData) {
		return budgetAppService.saveUser(userData);
	}
	
	@PutMapping("/user/update")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public UserData updateUser(@RequestParam Long userId, @RequestBody UserData userData) {
		userData.setUserId(userId);
		return budgetAppService.saveUser(userData);
	}
	
	@DeleteMapping("/user/delete")
	public Map<String, String> deleteUser(@RequestParam Long userId) {
		return budgetAppService.deleteUser(userId);
	}
	
	@GetMapping("/budgetbook/all")
	@ResponseBody
	public List<BudgetBookData> getAllBudgetBooks(){
		return budgetAppService.getAllBugetBooks();
	}
	
	@GetMapping("/budgetbook/{book_id}")
	@ResponseBody
	public BudgetBookData getBudgetBookById(@RequestParam Long budgetBookId){
		return budgetAppService.getBudgetBookById(budgetBookId);
	}
	
	@PostMapping("/user/{user_id}/budgetbook/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BudgetBookData saveBudgetBook(@PathVariable("user_id") Long userId, @RequestBody BudgetBookData budgetBookData) {
		return budgetAppService.saveBudgetBook(userId, budgetBookData);
	}
	
	@PutMapping("/user/{user_id}/budgetbook/update")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public BudgetBookData updateBudgetBook(@PathVariable("user_id") Long userId, @RequestParam Long budgetBookId, @RequestBody BudgetBookData budgetBookData) {
		budgetBookData.setBudgetBookId(budgetBookId);
		return budgetAppService.saveBudgetBook(userId, budgetBookData);
	}
	
	@DeleteMapping("/budgetbook")
	public Map<String, String> deleteBudgetBook(@RequestParam Long budgetBookId){
		return budgetAppService.deleteBudgetBook(budgetBookId);
	}
	
	@GetMapping("/budgetbook/{book_id}/transaction/all")
	@ResponseBody
	public List<TransactionData> getAllTransaction(@PathVariable("book_id") Long budgetBookId){
		return budgetAppService.getAllTransactions(budgetBookId);
	}
	
	@GetMapping("/budgetbook/{book_id}/transaction")
	@ResponseBody
	public TransactionData getTransactionById(@PathVariable("book_id") Long budgetBookId, Long transactionId) {
		return budgetAppService.getTransactionById(budgetBookId, transactionId);
	}
	
	@PostMapping("/budgetbook/{book_id}/transaction/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TransactionData saveTransaction(@PathVariable("book_id") Long budgetBookId, @RequestBody TransactionData transactionData) {
		return budgetAppService.saveTransaction(budgetBookId, transactionData);
	}
	
	@PutMapping("/budgetbook/{book_id}/transaction/update)")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public TransactionData updateTransaction(@PathVariable("book_id") Long budgetBookId,@RequestParam Long transactionId, @RequestBody TransactionData transactionData) {
		transactionData.setTransactionId(transactionId);
		return budgetAppService.saveTransaction(budgetBookId, transactionData);
	}
	
	@DeleteMapping("/budgetbook/{book_id}/transaction")
	public Map<String, String> deleteTransaction(@RequestParam Long transactionId){
		return budgetAppService.deleteTransaction(transactionId);
	}
	
	@GetMapping("/user/{user_id}/transaction/all")
	@ResponseBody
	public List<TransactionData> getAllUserTransactions(@PathVariable("user_id") Long userId){
		return budgetAppService.getAllUserTransactions(userId);
	}
}
