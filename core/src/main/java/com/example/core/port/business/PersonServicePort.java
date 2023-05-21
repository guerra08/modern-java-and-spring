package com.example.core.port.business;

import com.example.core.data.CreatePersonResult;
import com.example.core.data.DeletePersonResult;
import com.example.core.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonServicePort {
    /**
     * Create a person
     * @param personModel The person to create
     * @return The result of the operation.
     * If the operation was successful, the result will be a {@link CreatePersonResult.Success} containing the created person.
     * If the operation failed, the result will be a {@link CreatePersonResult.Error} containing the detailed message of the error.
     */
    CreatePersonResult create(PersonModel personModel);

    /**
     * Find all people
     * @return A list of all people
     */
    List<PersonModel> findAll();

    /**
     * Find a person by id
     * @param id The id of the person to find
     * @return The person if found, otherwise an empty optional
     */
    Optional<PersonModel> findPerson(UUID id);

    /**
     * Delete a person by id
     * @param id The id of the person to delete
     * @return The result of the operation.
     * If the operation was successful, the result will be a {@link DeletePersonResult.Success}.
     * If the operation failed due to the person not being found, the result will be a {@link DeletePersonResult.NotFound}.
     */
    DeletePersonResult deletePerson(UUID id);
}
