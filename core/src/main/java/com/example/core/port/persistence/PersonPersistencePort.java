package com.example.core.port.persistence;

import com.example.core.dto.request.CreatePersonRequest;
import com.example.core.dto.response.CreatePersonResponse;
import com.example.core.dto.response.PersonResponse;

import java.util.List;

public interface PersonPersistencePort {

    CreatePersonResponse save(CreatePersonRequest createPersonRequest);

    List<PersonResponse> findAll();
}
