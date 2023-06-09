package com.example.application.rest.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatePersonResponse(UUID id, String name, String surname, String email, LocalDateTime createdAt) {
}
