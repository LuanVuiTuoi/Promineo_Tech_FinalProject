package com.promineotech.budgetapp.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budgetBookId;
	@Column(unique=true)
	private String bookName;
	private BigDecimal bookTotalExpense;
	private BigDecimal bookTotalIncome;
	private String bookCreatedDate;
	
	@OneToMany(targetEntity=Transaction.class, cascade = CascadeType.ALL)
	@JoinColumn(name="budget_book_id", referencedColumnName="budgetBookId")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Transaction> transactions = new HashSet<Transaction>();
	
	@ManyToMany(mappedBy="userBudgetBooks")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<User> users = new HashSet<User>();
	
}
