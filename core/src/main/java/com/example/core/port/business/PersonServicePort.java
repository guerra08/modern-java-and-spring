package com.example.core.port.business;

import com.example.core.data.CreatePersonResult;
import com.example.core.data.DeletePersonResult;
import com.example.core.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonServicePort {
    CreatePersonResult create(PersonModel personModel);

    List<PersonModel> findAll();

    Optional<PersonModel> findPerson(UUID id);

    DeletePersonResult deletePerson(UUID id);
}
