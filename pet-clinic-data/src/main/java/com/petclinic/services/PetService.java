package com.petclinic.services;

import java.util.Set;

import com.petclinic.model.Pet;

public interface PetService {

	Pet findById(Long id);
	
	Pet save(Pet owner);
	
	Set<Pet> findAll();
}
