package com.petclinic.services.map;

import java.util.Set;

import com.petclinic.model.Owner;
import com.petclinic.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long>{
	
	// Ova metoda se samo nalazi u CrudService Interfejsu
	@Override
	public Owner save(Owner object) {
		return super.save(object.getId(), object);
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
}
