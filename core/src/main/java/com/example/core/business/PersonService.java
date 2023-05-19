package com.example.core.business;

import com.example.core.exception.PersonNotFoundException;
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
    public PersonModel create(PersonModel personModel) {
        return personPersistencePort.save(personModel);
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
    public void deletePerson(UUID id) {
        findPerson(id)
            .ifPresentOrElse(personPersistencePort::delete,
                () -> {
                    throw new PersonNotFoundException(String.format("Unable to find Person with id [%s].", id));
                });
    }
}
