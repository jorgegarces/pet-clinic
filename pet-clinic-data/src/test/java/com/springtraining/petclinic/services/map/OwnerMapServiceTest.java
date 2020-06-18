package com.springtraining.petclinic.services.map;

import com.springtraining.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final Owner testOwner = Owner.builder().id(ownerId).build();

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());

        ownerMapService.save(testOwner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

   @Test
    void delete() {
        ownerMapService.delete(testOwner);

        assertEquals(0, ownerMapService.findAll().size());

    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        ownerMapService.map.clear();
        ownerMapService.save(Owner.builder().id(2L).lastName("Petrov").build());
        Owner owner = ownerMapService.findByLastName("Petrov");

        assertNotNull(owner);
        assertEquals(2, owner.getId());
    }
}