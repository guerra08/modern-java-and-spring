package com.example.core.port.business;

import com.example.core.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonServicePort {
    PersonModel create(PersonModel personModel);

    List<PersonModel> findAll();

    Optional<PersonModel> findPerson(UUID id);

    void deletePerson(UUID id);
}
