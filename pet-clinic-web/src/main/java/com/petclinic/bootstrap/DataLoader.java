package com.petclinic.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetTypeService;
import com.petclinic.services.SpecialtyService;
import com.petclinic.services.VetService;
import com.petclinic.services.VisitService;
//kada spring bude potpuno spreman videce da ovaj bean implementira CommandLineRunner i pozvace metodu run
@Component 
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;
	
	@Autowired
	public DataLoader(OwnerService ownerService, VetService vetService, 
					PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}


	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if (count == 0) {
			loadData();
		}
	}


	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);
		
        Owner owner1 = Owner.builder()
        					.firstName("Michael")
        					.lastName("Daglas")
        					.address("123 Brickerel")
        					.city("Miami")
        					.phone("146-88-66")
        					.pets(new HashSet<>())
        					.build();
        
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        mikesPet.setVisits(new HashSet<>());
        owner1.getPets().add(mikesPet);
        
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Tony");
		owner2.setLastName("Soprano");
		owner2.setAddress("44 Mapple Street");
        owner2.setCity("Toronto");
        owner2.setPhone("66-6-556");
        owner2.setPets(new HashSet<>());
        
        Pet tonysPet = new Pet();
        tonysPet.setName("Zuca");
        tonysPet.setOwner(owner2);
        tonysPet.setBirthDate(LocalDate.now());
        tonysPet.setPetType(savedCatPetType);
        tonysPet.setVisits(new HashSet<>());
        owner2.getPets().add(tonysPet);
		
		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
        catVisit.setPet(tonysPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Jesica");
		vet1.setLastName("Andradge");
		vet1.setSpecialties(new HashSet<>());
		vet1.getSpecialties().add(savedDentistry);
		vet1.getSpecialties().add(savedRadiology);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Leonardo");
		vet2.setLastName("Da Vinci");
		vet2.setSpecialties(new HashSet<>());
		vet2.getSpecialties().add(savedSurgery);
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
	}

}
