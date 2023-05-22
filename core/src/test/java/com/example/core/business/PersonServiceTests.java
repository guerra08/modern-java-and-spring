package com.example.core.business;

import com.example.core.data.CreatePersonResult;
import com.example.core.data.DeletePersonResult;
import com.example.core.model.PersonModel;
import com.example.core.port.business.PersonServicePort;
import com.example.core.port.persistence.PersonPersistencePort;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("[Person Service Tests]")
class PersonServiceTests {

    private final PersonPersistencePort personPersistencePortMock = mock(PersonPersistencePort.class);
    private final PersonServicePort sut = new PersonService(personPersistencePortMock);

    @Test
    @DisplayName("Create creates a new person with success")
    void create_creates_new_person_with_success() {
        var mockedPerson = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.save(mockedPerson))
            .thenReturn(mockedPerson);

        var result = sut.create(mockedPerson);

        assertNotNull(result);
        assertInstanceOf(CreatePersonResult.Success.class, result);

        if (result instanceof CreatePersonResult.Success(var value)) {
            assertThat(value)
                .usingRecursiveComparison()
                .isEqualTo(mockedPerson);
            verify(personPersistencePortMock, times(1)).save(mockedPerson);
        }
    }

    @Test
    @DisplayName("Create fails on validation for duplicate e-mail")
    void create_fails_on_validation() {
        var mockedPerson = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.existsByEmail(mockedPerson.email()))
            .thenReturn(true);

        var result = sut.create(mockedPerson);

        assertNotNull(result);
        assertInstanceOf(CreatePersonResult.Error.class, result);

        if (result instanceof CreatePersonResult.Error(var reason)) {
            assertEquals(reason, "Email already exists.");
            verify(personPersistencePortMock, times(1)).existsByEmail(mockedPerson.email());
            verify(personPersistencePortMock, times(0)).save(mockedPerson);
        }
    }

    @Test
    @DisplayName("Find all returns all people")
    void findAll_returns_all_people() {
        var mockedPeople = Instancio.ofList(PersonModel.class).size(10).create();
        when(personPersistencePortMock.findAll()).thenReturn(mockedPeople);

        var result = sut.findAll();

        assertNotNull(result);
        assertEquals(result.size(), 10);

        verify(personPersistencePortMock, times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id returns Optional with value")
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
    @DisplayName("Find by id returns empty Optional")
    void findById_returns_empty_optional() {
        when(personPersistencePortMock.findById(any()))
            .thenReturn(Optional.empty());

        var result = sut.findPerson(UUID.randomUUID());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Delete person should delete person")
    void deletePerson_should_delete_person() {
        var mockedPersonModel = Instancio.of(PersonModel.class).create();
        when(personPersistencePortMock.findById(mockedPersonModel.id()))
            .thenReturn(Optional.of(mockedPersonModel));

        var result = sut.deletePerson(mockedPersonModel.id());

        assertNotNull(result);
        assertInstanceOf(DeletePersonResult.Success.class, result);
        verify(personPersistencePortMock, times(1)).delete(mockedPersonModel);
    }

    @Test
    @DisplayName("Delete person should return not found")
    void deletePerson_returns_not_found() {
        when(personPersistencePortMock.findById(any()))
            .thenReturn(Optional.empty());

        var result = sut.deletePerson(UUID.randomUUID());

        assertNotNull(result);
        assertInstanceOf(DeletePersonResult.NotFound.class, result);
    }

}
