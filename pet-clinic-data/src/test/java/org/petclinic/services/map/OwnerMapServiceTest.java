package org.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.Owner;
import com.petclinic.services.map.OwnerMapService;
import com.petclinic.services.map.PetMapService;
import com.petclinic.services.map.PetTypeMapService;

class OwnerMapServiceTest {
	
	OwnerMapService ownerMapService;
	
	final Long ownerId = 1L;
	final String lastName = "Smith";
	
	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	void testSaveExistingId() {
		Long id = 2L;
		
		Owner owner2 = Owner.builder().id(id).build();
		
		Owner savedOwner = ownerMapService.save(owner2);
		
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void testSaveNoId() {
		Owner savedOwner = ownerMapService.save(new Owner());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		
		assertEquals(1, ownerSet.size());
	}

	@Test
	void testFindById() {
		Owner owner = ownerMapService.findById(ownerId);
		
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testDeleteById() {
		ownerMapService.deleteById(ownerId);
		
		assertEquals(null, ownerMapService.findById(ownerId));
	}

	@Test
	void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);
		
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		Owner owner = ownerMapService.findByLastName("foo");
		
		assertNull(owner);
	}

}
