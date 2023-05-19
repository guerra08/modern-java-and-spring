package com.example.core.business;

import com.example.core.exception.PersonNotFoundException;
import com.example.core.model.PersonModel;
import com.example.core.port.business.PersonServicePort;
import com.example.core.port.persistence.PersonPersistencePort;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTests {

    private final PersonPersistencePort personPersistencePortMock = mock(PersonPersistencePort.class);
    private final PersonServicePort sut = new PersonService(personPersistencePortMock);

    @Test
    void create_creates_new_person_with_success() {
        var mockedPerson = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.save(mockedPerson))
            .thenReturn(mockedPerson);

        var result = sut.create(mockedPerson);

        assertNotNull(result);
        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedPerson);

        verify(personPersistencePortMock, times(1)).save(mockedPerson);
    }

    @Test
    void findAll_returns_all_people() {
        var mockedPeople = Instancio.ofList(PersonModel.class).size(10).create();
        when(personPersistencePortMock.findAll()).thenReturn(mockedPeople);

        var result = sut.findAll();

        assertNotNull(result);
        assertEquals(result.size(), 10);

        verify(personPersistencePortMock, times(1)).findAll();
    }

    @Test
    void findById_returns_optional_with_value() {
        var mockedPerson = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.findById(mockedPerson.id()))
            .thenReturn(Optional.of(mockedPerson));

        var result = sut.findPerson(mockedPerson.id());

        assertNotNull(result);
        assertTrue(result.isPresent());

        result.ifPresent(person ->
            assertThat(person)
                .usingRecursiveComparison()
                .isEqualTo(mockedPerson)
        );
    }

    @Test
    void findById_returns_empty_optional() {
        when(personPersistencePortMock.findById(any()))
            .thenReturn(Optional.empty());

        var result = sut.findPerson(UUID.randomUUID());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void deletePerson_should_delete_person() {
        var mockedPersonModel = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.findById(mockedPersonModel.id()))
            .thenReturn(Optional.of(mockedPersonModel));

        assertDoesNotThrow(() -> sut.deletePerson(mockedPersonModel.id()));
    }

    @Test
    void deletePerson_throws_if_person_does_not_exist() {
        when(personPersistencePortMock.findById(any()))
            .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class,
            () -> sut.deletePerson(UUID.randomUUID()));
    }

}
