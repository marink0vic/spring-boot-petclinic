package com.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.petclinic.model.Specialty;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
