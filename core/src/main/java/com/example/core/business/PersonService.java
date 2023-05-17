package com.example.core.business;

import com.example.core.dto.request.CreatePersonRequest;
import com.example.core.dto.response.CreatePersonResponse;
import com.example.core.dto.response.PersonResponse;
import com.example.core.port.business.PersonServicePort;
import com.example.core.port.persistence.PersonPersistencePort;

import java.util.List;

public class PersonService implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    public PersonService(PersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public CreatePersonResponse create(CreatePersonRequest createPersonRequest) {
        return personPersistencePort.save(createPersonRequest);
    }

    @Override
    public List<PersonResponse> findAll() {
        return personPersistencePort.findAll();
    }
}
