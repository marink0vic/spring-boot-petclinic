package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.petclinic.model.PetType;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
