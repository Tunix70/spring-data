package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Person;

import java.util.stream.Collectors;

public class PersonMapperDto {
    DepartmentMapperDto departmentMapperDto = new DepartmentMapperDto();
    LanguageMapperDto languageMapperDto = new LanguageMapperDto();
    DocumentMapperDto documentMapperDto = new DocumentMapperDto();

    public PersonDto mapToDepartmentDto(Person person) {
        PersonDto dto = new PersonDto();

        if (person != null) {
            dto.setId(person.getId());
            dto.setFirst_name(person.getFirst_name());
            dto.setSecond_name(person.getSecond_name());
            dto.setBirthday(person.getBirthday());
            dto.setDepartmentDto(departmentMapperDto.mapToDepartmentDto(person.getDepartment()));
            dto.setDocumentDto(documentMapperDto.mapToDepartmentDto(person.getDocument()));
            dto.setLanguages(person.getLanguageList().stream()
                                                .map(languageMapperDto :: mapToDepartmentDto)
                                                .collect(Collectors.toSet()));
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
            person.setDepartment(departmentMapperDto.mapToDepartmentEntity(dto.getDepartmentDto()));
            person.setDocument(documentMapperDto.mapToDepartmentEntity(dto.getDocumentDto()));
            person.setLanguageList(dto.getLanguages().stream()
                                                .map(languageMapperDto :: mapToDepartmentEntity)
                                                .collect(Collectors.toSet()));
            return person;
        } else
            return null;
    }
}
