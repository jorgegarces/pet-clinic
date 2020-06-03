package com.springtraining.petclinic.model;

import java.util.Set;

public class Owner extends Person {

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    private Set<Pet> pets;
}
