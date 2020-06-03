package com.springtraining.petclinic.bootstrap;

import com.springtraining.petclinic.model.Owner;
import com.springtraining.petclinic.model.Pet;
import com.springtraining.petclinic.model.PetType;
import com.springtraining.petclinic.model.Vet;
import com.springtraining.petclinic.services.OwnerService;
import com.springtraining.petclinic.services.PetTypeService;
import com.springtraining.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

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
        owner1.setFirstName("Felipe");
        owner1.setLastName("Fernandez");
        owner1.setAddress("Casa Josetas S/N");
        owner1.setCity("Barcelona");
        owner1.setTelephone("666666666");

        Pet feliPet = new Pet();
        feliPet.setPetType(savedDogPetType);
        feliPet.setOwner(owner1);
        feliPet.setName("Canelo");
        feliPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(feliPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manuel");
        owner2.setLastName("Galvez");
        owner2.setAddress("Calle Esperanza S/N");
        owner2.setCity("Martorell");
        owner2.setTelephone("666777777");

        Pet loloPet = new Pet();
        loloPet.setPetType(savedCatPetType);
        loloPet.setOwner(owner2);
        loloPet.setName("Matías");
        loloPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(loloPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ara");
        vet1.setLastName("Manjon");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Carolina");
        vet2.setLastName("Lopez");

        vetService.save(vet2);

        System.out.println("Loaded Vets ...");
    }
}
