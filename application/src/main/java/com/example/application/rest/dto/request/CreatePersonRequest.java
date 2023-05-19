package com.example.application.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(
    @NotBlank String name,
    @NotBlank String surname,
    @NotBlank
    @Email String email) {
}
