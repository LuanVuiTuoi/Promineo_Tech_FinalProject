package com.promineotech.pet.store.dao;

import com.promineotech.pet.store.entity.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {

}
