package com.promineotech.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.budgetapp.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
