package com.example.core.port.persistence;

import com.example.core.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {

    PersonModel save(PersonModel personModel);

    List<PersonModel> findAll();

    Optional<PersonModel> findById(UUID id);

    void delete(PersonModel person);
}
