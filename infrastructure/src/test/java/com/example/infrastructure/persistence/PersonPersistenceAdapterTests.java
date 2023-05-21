package com.example.infrastructure.persistence;

import com.example.core.model.PersonModel;
import com.example.core.port.persistence.PersonPersistencePort;
import com.example.infrastructure.entity.Person;
import com.example.infrastructure.repository.PersonRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonPersistenceAdapterTests {

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final PersonPersistencePort sut = new PersonPersistenceAdapter(personRepository);

    @Test
    void findAll_returns_people() {
        var mockedEntities = Instancio.ofList(Person.class).size(10).create();
        when(personRepository.findAll()).thenReturn(mockedEntities);

        var result = sut.findAll();

        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    void findById_returns_empty_optional() {
        when(personRepository.findById(any())).thenReturn(Optional.empty());

        var result = sut.findById(UUID.randomUUID());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findById_returns_populated_optional() {
        var mockedPerson = Instancio.of(Person.class).create();
        when(personRepository.findById(any())).thenReturn(Optional.of(mockedPerson));

        var result = sut.findById(UUID.randomUUID());

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    void create_returns_created_person() {
        var mockedPersonModel = Instancio.of(PersonModel.class).create();
        var mockedPerson = Instancio.of(Person.class).create();
        when(personRepository.existsByEmail(mockedPersonModel.email()))
            .thenReturn(false);
        when(personRepository.save(any()))
            .thenReturn(mockedPerson);

        var result = sut.save(mockedPersonModel);

        assertNotNull(result);
        assertThat(result)
            .usingRecursiveComparison()
            .ignoringFields("id", "createdAt")
            .isEqualTo(mockedPersonModel);
    }

    @Test
    void delete_should_delete_person() {
        var mockedPersonModel = Instancio.of(PersonModel.class).create();

        assertDoesNotThrow(() -> sut.delete(mockedPersonModel));
    }

}
