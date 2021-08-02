package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonMapperDto {
    DepartmentMapperDto departmentMapperDto;
    LanguageMapperDto languageMapperDto;
    DocumentMapperDto documentMapperDto;

    @Autowired
    public PersonMapperDto(DepartmentMapperDto departmentMapperDto, LanguageMapperDto languageMapperDto, DocumentMapperDto documentMapperDto) {
        this.departmentMapperDto = departmentMapperDto;
        this.languageMapperDto = languageMapperDto;
        this.documentMapperDto = documentMapperDto;
    }

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
