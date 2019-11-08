package com.petclinic.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", 
			   joinColumns = @JoinColumn(name = "vet_id"),
			   inverseJoinColumns = @JoinColumn(name = "speciality_id"))
	private Set<Specialty> specialties;	

}
