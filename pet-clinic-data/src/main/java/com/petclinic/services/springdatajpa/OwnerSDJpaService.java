package com.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Owner;
import com.petclinic.repositories.OwnerRepository;
import com.petclinic.repositories.PetRepository;
import com.petclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;

	@Autowired
	public OwnerSDJpaService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public Set<Owner> findAll() {
		Iterable<Owner> ownersIterable = ownerRepository.findAll();
		Set<Owner> owners = new HashSet<>();
		ownersIterable.forEach(owners::add);
		
		return owners;
	}

	@Override
	public Owner findById(Long id) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		
		return optionalOwner.orElse(null);
	}

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Owner owner) {
		ownerRepository.delete(owner);
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		Owner owner = ownerRepository.findByLastName(lastName);
		
		return owner;
	}
}
