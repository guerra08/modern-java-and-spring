package com.example.core.data;

import com.example.core.model.PersonModel;

public sealed interface CreatePersonResult {

    record Success(PersonModel value) implements CreatePersonResult {
    }

    record Error(String reason) implements CreatePersonResult {
    }

}
