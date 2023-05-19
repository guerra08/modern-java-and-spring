package com.example.application.configuration;

import com.example.core.business.PersonService;
import com.example.core.port.business.PersonServicePort;
import com.example.core.port.persistence.PersonPersistencePort;
import com.example.infrastructure.persistence.PersonPersistenceAdapter;
import com.example.infrastructure.repository.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PersonPersistencePort personPersistencePort(
        PersonRepository personRepository) {
        return new PersonPersistenceAdapter(personRepository);
    }

    @Bean
    public PersonServicePort personServicePort(PersonPersistencePort personPersistencePort) {
        return new PersonService(personPersistencePort);
    }

}
