package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonMapperDtoTest {
    private final PersonMapperDto personMapperDto = new PersonMapperDto();

    @Test
    @DisplayName("Method should map from entity to dto")
    void shouldMapToDtoFromEntity() {
        //given
        Date date = new Date(12345L);
        Department department = new Department(1L, "Department");
        Set<Language> languageList = Set.of(new Language(1L, "RU"), new Language(2L, "EU"));
        Document document = new Document("ert-234-fsd", date);

        Person person = new Person(1L, "Oleg", "Egorov", date, department, languageList, document);
        Person person1 = null;

        //when
        PersonDto dto = personMapperDto.mapToPersonDto(person);
        PersonDto dto1 = personMapperDto.mapToPersonDto(person1);

        //than
        assertThat(dto.getId()).isEqualTo(person.getId());
        assertThat(dto.getFirst_name()).isEqualTo(person.getFirst_name());
        assertThat(dto.getSecond_name()).isEqualTo(person.getSecond_name());
        assertThat(dto.getBirthday()).isEqualTo(person.getBirthday());
        assertThat(dto.getDepartment()).isEqualTo(person.getDepartment().getId());
        assertThat(dto.getLanguages()).isEqualTo(person.getLanguageList()
                        .stream()
                        .map(Language::getId)
                        .collect(Collectors.toSet()));
        assertThat(dto.getDocument()).isEqualTo(person.getDocument().getId());

        assertThat(dto1).isNull();
    }

    @Test
    @DisplayName("Method should map from dto to entity")
    void shouldMapToEntityFromDto() {
        //given
        Date date = new Date(12345L);
        PersonDto dto1 = null;
        PersonDto dto = new PersonDto(1L, "Ivan", "Ivanov", date);

        //when
        Person person = personMapperDto.mapToPersonEntity(dto);
        Person person1 = personMapperDto.mapToPersonEntity(dto1);

        //than
        assertThat(person.getId()).isEqualTo(dto.getId());
        assertThat(person.getFirst_name()).isEqualTo(dto.getFirst_name());
        assertThat(person.getSecond_name()).isEqualTo(dto.getSecond_name());
        assertThat(person.getBirthday()).isEqualTo(dto.getBirthday());

        assertThat(person1).isNull();
    }
}