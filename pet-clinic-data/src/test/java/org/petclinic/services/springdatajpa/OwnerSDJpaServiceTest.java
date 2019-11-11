package org.petclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.model.Owner;
import com.petclinic.repositories.OwnerRepository;
import com.petclinic.services.springdatajpa.OwnerSDJpaService;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	private static final String LAST_NAME = "Smith";
	Owner returnOwner;
	
	@Mock
	OwnerRepository ownerRepository;
	
	@InjectMocks
	OwnerSDJpaService service;

	@BeforeEach
	void setUp() throws Exception {
		// reset before each test method
		returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1l).build());
        returnOwnersSet.add(Owner.builder().id(2l).build());

             //when this called 		           return this
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
	}
	
	@Test
    void testFindByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);

        //default is 1 times
		verify(ownerRepository).delete(any());
        //verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
	}

	@Test
	void testFindByLastName() {
		
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, smith.getLastName());
		
		verify(ownerRepository).findByLastName(any());
	}

}
