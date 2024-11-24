package com.promineotech.pet.store.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.promineotech.pet.store.controller.model.PetStoreCustomer;
import com.promineotech.pet.store.controller.model.PetStoreData;
import com.promineotech.pet.store.controller.model.PetStoreEmployee;
import com.promineotech.pet.store.service.PetStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pet_store")
@Slf4j 
public class PetStoreController {
	
	@Autowired
	private PetStoreService petStoreService;
	

	@GetMapping()
	@ResponseBody
	public List<PetStoreData> getAllPetStore(){
		return petStoreService.getAllPetStore();
	}
	
	@GetMapping("/{petStoreId}")
	@ResponseBody
	public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
		return petStoreService.getPetStoreById(petStoreId);
	}
	
	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting Pet Store with ID: {}", petStoreId);
		return petStoreService.deletePetStoreById(petStoreId);
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData savePetStore(@RequestBody PetStoreData petStoreData) {
		log.info("PetStoreData Created {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee savePetStoreEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Employee {} Created for Pet Store {}", petStoreEmployee, petStoreId);
		return petStoreService.savePetStoreEmployee(petStoreId, petStoreEmployee);
	}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer savePetStoreCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Customer {} Created for Pet Store {}", petStoreCustomer, petStoreId);
		return petStoreService.savePetStoreCustomer(petStoreId, petStoreCustomer);
	}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStoreById(@RequestParam Long petStoreId, @RequestBody PetStoreData petStoreData) {
		log.info("PetStore Id {} was UpDated {}", petStoreId, petStoreData);
		petStoreData.setPetStoreId(petStoreId);
		return petStoreService.savePetStore(petStoreData);
		
	}
	
}
