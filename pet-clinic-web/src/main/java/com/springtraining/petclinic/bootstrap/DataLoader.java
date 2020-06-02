package com.springtraining.petclinic.bootstrap;

import com.springtraining.petclinic.model.Owner;
import com.springtraining.petclinic.model.Vet;
import com.springtraining.petclinic.services.OwnerService;
import com.springtraining.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Felipe");
        owner1.setLastName("Fernandez");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Manuel");
        owner2.setLastName("Galvez");

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
