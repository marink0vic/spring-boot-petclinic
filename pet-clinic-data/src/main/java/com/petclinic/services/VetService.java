package com.petclinic.services;

import java.util.Set;

import com.petclinic.model.Vet;


public interface VetService {

	Vet findById(Long id);
	
	Vet save(Vet owner);
	
	Set<Vet> findAll();
}
