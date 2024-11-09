package com.promineotech.pet.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.pet.store.controller.model.PetStoreData;
import com.promineotech.pet.store.dao.PetStoreDao;
import com.promineotech.pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	
	public PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao
				.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet Store Id: " + petStoreId + " was not found."));
	}
	
	public PetStore findOrCreatePetStore(Long petStoreId) {
		return Objects.isNull(petStoreId) ? new PetStore() : findPetStoreById(petStoreId);
	}
	
	public PetStore copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		
		return petStore;
	}
	
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		
		return new PetStoreData(petStoreDao.save(petStore));
	}

	public List<PetStore> findAll() {
		return petStoreDao.findAll();
	}
	
}
