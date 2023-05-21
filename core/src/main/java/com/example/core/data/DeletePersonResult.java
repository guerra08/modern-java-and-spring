package com.example.core.data;

public sealed interface DeletePersonResult {
    record Success() implements DeletePersonResult {
    }

    record NotFound() implements DeletePersonResult {
    }
}
