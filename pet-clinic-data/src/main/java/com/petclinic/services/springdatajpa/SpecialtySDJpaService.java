package com.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Specialty;
import com.petclinic.repositories.SpecialtyRepository;
import com.petclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {
	
	private final SpecialtyRepository specialtyRepository;

	@Autowired
	public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialties = new HashSet<>();
		specialtyRepository.findAll().forEach(specialties::add);
		
		return specialties;
	}

	@Override
	public Specialty findById(Long id) {
		return specialtyRepository.findById(id).orElse(null);
	}

	@Override
	public Specialty save(Specialty specialty) {
		return specialtyRepository.save(specialty);
	}

	@Override
	public void delete(Specialty specialty) {
		specialtyRepository.delete(specialty);
	}

	@Override
	public void deleteById(Long id) {
		specialtyRepository.deleteById(id);
	}

}
