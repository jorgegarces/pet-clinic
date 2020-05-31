package com.springtraining.petclinic.services;

import com.springtraining.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
