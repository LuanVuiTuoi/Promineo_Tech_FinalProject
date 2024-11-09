package com.promineotech.pet.store.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.promineotech.pet.store.controller.model.PetStoreData;
import com.promineotech.pet.store.entity.PetStore;
import com.promineotech.pet.store.service.PetStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pet_store")
@Slf4j 
public class PetStoreController {
	
	@Autowired
	private PetStoreService petStoreService;
	

	@GetMapping("/pet_store")
	public List<PetStore> getAllPetStore(){
		return petStoreService.findAll();
	}

	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData savePetStore(@RequestBody PetStoreData petStoreData) {
		log.info("PetStoreData Created {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/pet_store/{id}")
	public PetStoreData updatePetStoreById(@RequestParam Long petStoreId, @RequestBody PetStoreData petStoreData) {
		log.info("PetStore Id {} was UpDated {}", petStoreId, petStoreData);
		petStoreData.setPetStoreId(petStoreId);
		return petStoreService.savePetStore(petStoreData);
		
	}
	
}
