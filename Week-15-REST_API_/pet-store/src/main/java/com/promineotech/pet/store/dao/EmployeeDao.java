package com.promineotech.pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.pet.store.entity.Employee;

public interface EmployeeDao extends  JpaRepository<Employee, Long>  {

}
