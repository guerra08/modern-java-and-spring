package com.example.infrastructure.mapper;

import com.example.core.dto.response.CreatePersonResponse;
import com.example.core.dto.response.PersonResponse;
import com.example.infrastructure.entity.Person;

import java.util.List;

public final class PersonMapper {

    public static PersonResponse toPersonResponse(Person person) {
        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getSurname(),
                person.getEmail(),
                person.getCreatedAt()
        );
    }

    public static List<PersonResponse> toPersonResponseList(List<Person> people) {
        return people.stream().map(PersonMapper::toPersonResponse).toList();
    }

    public static CreatePersonResponse toCreatePersonResponse(Person person) {
        return new CreatePersonResponse(
                person.getId(),
                person.getName(),
                person.getSurname(),
                person.getEmail(),
                person.getCreatedAt()
        );
    }

}
