package com.promineotech.pet.store.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.pet.store.controller.model.PetStoreCustomer;
import com.promineotech.pet.store.controller.model.PetStoreData;
import com.promineotech.pet.store.controller.model.PetStoreEmployee;
import com.promineotech.pet.store.dao.CustomerDao;
import com.promineotech.pet.store.dao.EmployeeDao;
import com.promineotech.pet.store.dao.PetStoreDao;
import com.promineotech.pet.store.entity.Customer;
import com.promineotech.pet.store.entity.Employee;
import com.promineotech.pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional(readOnly = true)
	public List<PetStoreData> getAllPetStore() {
		List<PetStoreData> petStoreList = new LinkedList<PetStoreData>();
		for(PetStore petStore : petStoreDao.findAll()) {
			PetStoreData petStoreData = new PetStoreData(petStore);	
			petStoreData.getCustomers().clear();
			petStoreData.getEmployees().clear();
			petStoreList.add(petStoreData);
		}
		
		return petStoreList;
		
	}
	
	@Transactional(readOnly = true)
	public PetStoreData getPetStoreById(Long petStoreId) {
		return new PetStoreData(findPetStoreById(petStoreId));
	}
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		
		return new PetStoreData(petStoreDao.save(petStore));
	}
	
	
	public Map<String, String> deletePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		petStoreDao.delete(petStore);
		
		return Collections.singletonMap("message", "Pet Store with ID: " + petStoreId + " has been deleted.");
	}
	
	public PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao
				.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet Store Id: " + petStoreId + " was not found."));
	}
	
	public PetStore findOrCreatePetStore(Long petStoreId) {
		return Objects.isNull(petStoreId) ? new PetStore() : findPetStoreById(petStoreId);
	}
	
	public void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}
	
	
	
	@Transactional(readOnly = false)
	public PetStoreEmployee savePetStoreEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		PetStore petStore = findPetStoreById(petStoreId);
		Long employeeId = petStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(petStoreId, employeeId);
		
		copyEmployeeFields(petStoreEmployee, employee);
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		
		return new PetStoreEmployee(employeeDao.save(employee));
	}

	private void copyEmployeeFields(PetStoreEmployee petStoreEmployee, Employee employee) {
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
		
	}

	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
		return Objects.isNull(employeeId) ? new Employee() : findEmployeeById(petStoreId, employeeId);
	}
	
	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		Employee employee = employeeDao
				.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException("Employee Id: " + employeeId + " was not found."));
		if(Objects.equals(petStoreId, employee.getPetStore().getPetStoreId())) throw new IllegalArgumentException("PetStore Id does not match.");
		
		return employee;
	}
	
	@Transactional(readOnly = false)
	public PetStoreCustomer savePetStoreCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId);
		Long customerId = petStoreCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(petStoreId, customerId);
		
		copyCustomerFields(petStoreCustomer, customer);
		customer.getPetStores().add(petStore);
		petStore.getCustomers().add(customer);
		
		return new PetStoreCustomer(customerDao.save(customer));
	}

	private void copyCustomerFields(PetStoreCustomer petStoreCustomer, Customer customer) {
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		
	}

	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		return Objects.isNull(customerId) ? new Customer() : findCustomerById(petStoreId,customerId);
	}

	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao
				.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer Id: " + customerId + " was not found."));
		PetStore petStore = customer
								.getPetStores()
									.stream()
									.filter(ps -> ps.getPetStoreId().equals(petStoreId))
									.findFirst()
									.orElse(null);
		
		if(petStore == null) throw new IllegalArgumentException("PetStore Id does not match.");
		
		return customer;
	}


	
}
