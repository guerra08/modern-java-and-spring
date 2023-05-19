package com.example.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record PersonModel(UUID id, String name, String surname, String email, LocalDateTime createdAt) {
}
