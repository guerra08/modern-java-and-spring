package com.example.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "person")
@Entity
public class Person {

    @Id
    private UUID id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private LocalDateTime createdAt;

}
