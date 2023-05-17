package com.example.application.rest;

import com.example.core.dto.request.CreatePersonRequest;
import com.example.core.port.business.PersonServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServicePort personServicePort;

    public PersonController(PersonServicePort personServicePort) {
        this.personServicePort = personServicePort;
    }

    @GetMapping
    public ResponseEntity<Object> getPeople() {
        var people = personServicePort.findAll();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<Object> createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest) {
        var result = personServicePort.create(createPersonRequest);
        return ResponseEntity.created(URI.create("/person/" + result.id())).body(result);
    }

}
