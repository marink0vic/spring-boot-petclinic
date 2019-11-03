package com.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetService;
import com.petclinic.services.PetTypeService;
import com.petclinic.services.VetService;
//kada spring bude potpuno spreman videce da ovaj bean implementira CommandLineRunner i pozvace metodu run
@Component 
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	@Autowired
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}


	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Daglas");
		owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setPhone("148-66-687");
        
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Tony");
		owner2.setLastName("Soprano");
		owner2.setAddress("44 Mapple Street");
        owner2.setCity("Toronto");
        owner2.setPhone("66-6-556");
        
        Pet tonysPet = new Pet();
        tonysPet.setName("Zuca");
        tonysPet.setOwner(owner2);
        tonysPet.setBirthDate(LocalDate.now());
        tonysPet.setPetType(savedCatPetType);
        owner2.getPets().add(tonysPet);
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Jesica");
		vet1.setLastName("Andradge");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Leonardo");
		vet2.setLastName("Da Vinci");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
		
	}

}
