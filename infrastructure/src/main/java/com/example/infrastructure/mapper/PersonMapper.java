package com.example.infrastructure.mapper;

import com.example.core.model.PersonModel;
import com.example.infrastructure.entity.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonMapper {

    public static PersonModel toModel(Person person) {
        return new PersonModel(
            person.getId(),
            person.getName(),
            person.getSurname(),
            person.getEmail(),
            person.getCreatedAt()
        );
    }

    public static Person toEntity(PersonModel personModel) {
        return Person.builder()
            .id(personModel.id())
            .email(personModel.email())
            .name(personModel.name())
            .surname(personModel.surname())
            .createdAt(personModel.createdAt()).build();
    }

    public static List<PersonModel> toModelList(List<Person> people) {
        return people.stream().map(PersonMapper::toModel).toList();
    }

}
