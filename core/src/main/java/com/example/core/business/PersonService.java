package com.example.core.business;

import com.example.core.data.CreatePersonResult;
import com.example.core.data.DeletePersonResult;
import com.example.core.model.PersonModel;
import com.example.core.port.business.PersonServicePort;
import com.example.core.port.persistence.PersonPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonService implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    public PersonService(PersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public CreatePersonResult create(PersonModel personModel) {
        if (personPersistencePort.existsByEmail(personModel.email())) {
            return new CreatePersonResult.Error("Email already exists.");
        }
        return new CreatePersonResult.Success(personPersistencePort.save(personModel));
    }

    @Override
    public List<PersonModel> findAll() {
        return personPersistencePort.findAll();
    }

    @Override
    public Optional<PersonModel> findPerson(UUID id) {
        return personPersistencePort.findById(id);
    }

    @Override
    public DeletePersonResult deletePerson(UUID id) {
        return findPerson(id)
            .<DeletePersonResult>map(person -> {
                personPersistencePort.delete(person);
                return new DeletePersonResult.Success();
            })
            .orElse(new DeletePersonResult.NotFound());
    }
}
