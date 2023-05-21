package com.example.core.port.persistence;

import com.example.core.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {

    /**
     * Saves a person in the persistence layer
     * @param personModel The PersonModel representation
     * @return PersonModel The persisted PersonModel
     */
    PersonModel save(PersonModel personModel);

    /**
     * Checks if a person exists by email
     * @param email The email to check
     * @return boolean true if the person exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds all the people in the persistence layer
     * @return List<PersonModel> The list of people mapped to models
     */
    List<PersonModel> findAll();

    /**
     * Finds a person by id
     * @param id The id to search for
     * @return Optional<PersonModel> The person if found, empty otherwise
     */
    Optional<PersonModel> findById(UUID id);

    /**
     * Deletes a person from the persistence layer
     * @param person The person to delete
     */
    void delete(PersonModel person);
}
