package com.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petclinic.model.Owner;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.VetService;
import com.petclinic.services.map.OwnerServiceMap;
import com.petclinic.services.map.VetServiceMap;
//kada spring bude potpuno spreman videce da ovaj bean implementira CommandLineRunner i pozvace metodu run
@Component 
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	
	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Daglas");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Tony");
		owner2.setLastName("Soprano");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Jesica");
		vet1.setLastName("Andradge");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Leonardo");
		vet2.setLastName("Da Vinci");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
		
	}

}
