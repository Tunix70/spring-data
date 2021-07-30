package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Person;

public class PersonMapperDto {
    public PersonDto mapToDepartmentDto(Person person) {
        PersonDto dto = new PersonDto();

        if (person != null) {
            dto.setId(person.getId());
            dto.setFirst_name(person.getFirst_name());
            dto.setSecond_name(person.getSecond_name());
            dto.setBirthday(person.getBirthday());
//            dto.setDepartment(person.getDepartment());
//            dto.setDocument(person.getDocument());
//            dto.setLanguageList(person.getLanguageList());
            return dto;
        } else
            return null;
    }

    public Person mapToDepartmentEntity(PersonDto dto) {
        Person person = new Person();

        if (dto != null) {
            person.setId(dto.getId());
            person.setFirst_name(dto.getFirst_name());
            person.setSecond_name(dto.getSecond_name());
            person.setBirthday(dto.getBirthday());
            person.setDepartment(dto.getDepartment());
            person.setDocument(dto.getDocument());
            person.setLanguageList(dto.getLanguageList());
            return person;
        } else
            return null;
    }
}
