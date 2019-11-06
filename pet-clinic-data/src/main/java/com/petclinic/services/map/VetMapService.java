package com.petclinic.services.map;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.services.VetService;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
	
	private final SpecialtyMapService specialityServiceMap;
	
	@Autowired
	public VetMapService(SpecialtyMapService specialityServiceMap) {
		this.specialityServiceMap = specialityServiceMap;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet object) {
		if (object != null) {
			object.getSpecialties().forEach(speciality -> {
				if (speciality.getId() == null) {
					Specialty savedSpeciality = specialityServiceMap.save(speciality);
					speciality.setId(savedSpeciality.getId());
				}
			});
			
			return super.save(object);
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	

}
