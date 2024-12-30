package com.promineotech.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.budgetapp.entity.BudgetBook;

public interface BudgetBookRepository extends JpaRepository<BudgetBook, Long> {

}
