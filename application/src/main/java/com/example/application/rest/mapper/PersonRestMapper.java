package com.example.application.rest.mapper;

import com.example.application.rest.dto.request.CreatePersonRequest;
import com.example.application.rest.dto.response.CreatePersonResponse;
import com.example.application.rest.dto.response.PersonResponse;
import com.example.core.model.PersonModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonRestMapper {

    PersonModel toModel(CreatePersonRequest createPersonRequest);

    CreatePersonResponse toCreatePersonResponse(PersonModel model);

    PersonResponse toPersonResponse(PersonModel model);

}
