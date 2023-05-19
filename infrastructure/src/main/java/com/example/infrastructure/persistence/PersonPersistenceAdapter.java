package com.example.infrastructure.persistence;

import com.example.core.exception.DuplicatePersonEmailException;
import com.example.core.model.PersonModel;
import com.example.core.port.persistence.PersonPersistencePort;
import com.example.infrastructure.entity.Person;
import com.example.infrastructure.mapper.PersonMapper;
import com.example.infrastructure.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonPersistenceAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;

    public PersonPersistenceAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonModel save(PersonModel personModel) {
        if (personRepository.existsByEmail(personModel.email())) {
            throw new DuplicatePersonEmailException("A person with the same e-mail already exists!");
        }
        Person person = Person.builder()
            .id(UUID.randomUUID())
            .email(personModel.email())
            .name(personModel.name())
            .surname(personModel.surname())
            .createdAt(LocalDateTime.now())
            .build();
        personRepository.save(person);
        return PersonMapper.toModel(person);
    }

    @Override
    public List<PersonModel> findAll() {
        return PersonMapper.toModelList(personRepository.findAll());
    }

    @Override
    public Optional<PersonModel> findById(UUID id) {
        return personRepository.findById(id)
            .map(PersonMapper::toModel);
    }

    @Override
    public void delete(PersonModel person) {
        personRepository.delete(PersonMapper.toEntity(person));
    }
}
