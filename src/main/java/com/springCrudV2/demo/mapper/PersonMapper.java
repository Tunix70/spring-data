package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonDto mapToPersonDto(Person person) {
        PersonDto dto = new PersonDto();

        if (person != null) {
            dto.setId(person.getId());
            dto.setFirst_name(person.getFirst_name());
            dto.setSecond_name(person.getSecond_name());
            dto.setBirthday(person.getBirthday());
            dto.setDepartment(person.getDepartment().getId());
            dto.setDocument(person.getDocument().getId());
            dto.setLanguages(person.getLanguageList().stream()
                    .map(Language :: getId)
                    .collect(Collectors.toSet()));
            return dto;
        } else
            return null;
    }

    public Person mapToPersonEntity(PersonDto dto) {
        Person person = new Person();

        if (dto != null) {
            person.setId(dto.getId());
            person.setFirst_name(dto.getFirst_name());
            person.setSecond_name(dto.getSecond_name());
            person.setBirthday(dto.getBirthday());
            return person;
        } else
            return null;
    }
}
