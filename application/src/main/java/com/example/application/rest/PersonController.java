package com.example.application.rest;

import com.example.application.rest.dto.request.CreatePersonRequest;
import com.example.application.rest.dto.response.PersonResponse;
import com.example.application.rest.mapper.PersonRestMapper;
import com.example.core.data.CreatePersonResult;
import com.example.core.data.DeletePersonResult;
import com.example.core.port.business.PersonServicePort;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@Observed(name = "personController")
@Slf4j
public class PersonController {

    private final PersonServicePort personServicePort;
    private final PersonRestMapper personRestMapper;

    public PersonController(
        PersonServicePort personServicePort,
        PersonRestMapper personRestMapper) {
        this.personServicePort = personServicePort;
        this.personRestMapper = personRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        var people = personServicePort.findAll().stream()
            .map(personRestMapper::toPersonResponse)
            .toList();
        return ResponseEntity.ok(people);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable UUID id) {
        return personServicePort.findPerson(id)
            .map(personModel -> ResponseEntity.ok(personRestMapper.toPersonResponse(personModel)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PersonResponse> deletePerson(@PathVariable UUID id) {
        return switch (personServicePort.deletePerson(id)) {
            case DeletePersonResult.Success() -> ResponseEntity.noContent().build();
            case DeletePersonResult.NotFound() -> ResponseEntity.notFound().build();
        };
    }

    @PostMapping
    public ResponseEntity<Object> createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest) {
        var result = personServicePort.create(personRestMapper.toModel(createPersonRequest));
        return switch (result) {
            case CreatePersonResult.Success(var value) ->
                ResponseEntity.created(URI.create("/person/" + value.id())).body(personRestMapper.toCreatePersonResponse(value));
            case CreatePersonResult.Error(var reason) -> ResponseEntity.badRequest().body(reason);
        };
    }

}
