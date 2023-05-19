package com.example.infrastructure.repository;

import com.example.infrastructure.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    boolean existsByEmail(String email);

}
