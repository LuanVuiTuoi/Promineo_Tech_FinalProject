package com.promineotech.pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.pet.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer,Long>{

}
