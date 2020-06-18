package com.springtraining.petclinic.services.springdatajpa;

import com.springtraining.petclinic.model.Owner;
import com.springtraining.petclinic.repositories.OwnerRepository;
import com.springtraining.petclinic.repositories.PetRepository;
import com.springtraining.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    final String LAST_NAME = "Smith";
    final Owner owner1 = Owner.builder().id(1L).build();
    final Owner owner2 = Owner.builder().lastName("Smith").build();
    Set<Owner> owners = new HashSet<>();

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        owners.add(owner1);
        owners.add(owner2);
    }

    @Test
    void findAll() {

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> foundOwners = service.findAll();

        assertEquals(2, foundOwners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(owner1));

        assertEquals(1, service.findById(1L).getId());
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByLastName() throws Exception {

        Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(1, smith.getId());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void save() {

        when(ownerRepository.save(owner1)).thenReturn(owner1);

        assertEquals(owner1, service.save(owner1));
    }

    @Test
    void delete() {

        service.delete(owner1);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {

        service.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(1L);
    }
}