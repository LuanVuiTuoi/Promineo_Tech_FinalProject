package com.promineotech.pet.store.controller.model;

import com.promineotech.pet.store.entity.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PetStoreEmployee {
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	
	public PetStoreEmployee(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.employeeFirstName = employee.getEmployeeFirstName();
		this.employeeLastName = employee.getEmployeeLastName();
		this.employeePhone = employee.getEmployeePhone();
		this.employeeJobTitle = employee.getEmployeeJobTitle();
	}
	
	

}
