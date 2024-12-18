package com.promineotech.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.budgetapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
