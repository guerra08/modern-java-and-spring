package com.example.infrastructure.persistence;

import com.example.core.dto.request.CreatePersonRequest;
import com.example.core.dto.response.CreatePersonResponse;
import com.example.core.dto.response.PersonResponse;
import com.example.core.port.persistence.PersonPersistencePort;
import com.example.infrastructure.entity.Person;
import com.example.infrastructure.mapper.PersonMapper;
import com.example.infrastructure.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PersonPersistenceAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;

    public PersonPersistenceAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public CreatePersonResponse save(CreatePersonRequest createPersonRequest) {
        Person person = Person.builder()
                .id(UUID.randomUUID())
                .email(createPersonRequest.email())
                .name(createPersonRequest.name())
                .surname(createPersonRequest.surname())
                .createdAt(LocalDateTime.now())
                .build();
        personRepository.save(person);
        return PersonMapper.toCreatePersonResponse(person);
    }

    @Override
    public List<PersonResponse> findAll() {
        return PersonMapper.toPersonResponseList(personRepository.findAll());
    }
}
