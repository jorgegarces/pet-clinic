package com.springtraining.petclinic.bootstrap;

import com.springtraining.petclinic.model.*;
import com.springtraining.petclinic.services.OwnerService;
import com.springtraining.petclinic.services.PetTypeService;
import com.springtraining.petclinic.services.SpecialityService;
import com.springtraining.petclinic.services.VetService;
import org.hibernate.type.SpecialOneToOneType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) loadData();
    }

    private void loadData() {
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

        Speciality dogSpecialist = new Speciality();
        dogSpecialist.setDescription("Dog specialist");
        Speciality savedDogSpecialist = specialityService.save(dogSpecialist);

        Speciality catSpecialist = new Speciality();
        catSpecialist.setDescription("Cat specialist");
        Speciality savedCatSpecialist = specialityService.save(catSpecialist);

        Speciality surgery = new Speciality();
        surgery.setDescription("Expert Surgeon");
        Speciality savedSurgerySpecialist = specialityService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Ara");
        vet1.setLastName("Manjon");
        vet1.getSpecialities().add(savedCatSpecialist);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Carolina");
        vet2.setLastName("Lopez");
        vet2.getSpecialities().add(savedDogSpecialist);

        vetService.save(vet2);

        System.out.println("Loaded Vets ...");
    }
}
