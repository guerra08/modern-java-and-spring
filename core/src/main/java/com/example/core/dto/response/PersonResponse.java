package com.example.core.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PersonResponse(UUID id, String name, String surname, String email, LocalDateTime createdAt) {
}
