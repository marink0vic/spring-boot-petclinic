package com.petclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.petclinic.model.Owner;
import com.petclinic.services.OwnerService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
	
	// Ova metoda se samo nalazi u CrudService Interfejsu
	@Override
	public Owner save(Owner object) {
		return super.save(object);
	}
	
	//Ove ostale metode se nalaze i u AbstractServiceMap i u CrudService interfejsu
	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
}
