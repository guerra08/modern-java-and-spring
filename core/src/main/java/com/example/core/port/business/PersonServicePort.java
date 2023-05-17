package com.example.core.port.business;

import com.example.core.dto.request.CreatePersonRequest;
import com.example.core.dto.response.CreatePersonResponse;
import com.example.core.dto.response.PersonResponse;

import java.util.List;

public interface PersonServicePort {
    CreatePersonResponse create(CreatePersonRequest createPersonRequest);
    List<PersonResponse> findAll();
}
