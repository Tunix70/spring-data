package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PersonMapperTest {
    private final PersonMapper personMapper = new PersonMapper();

    @Test
    void shouldMapToDtoFromEntity() {
        //given
        Person person = getPerson();
        PersonDto expected = getPersonDto();

        //when
        PersonDto result = personMapper.mapToPersonDto(person);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetNullWhenMapToDtoFromEntityWhereEntityIsNull() {
        //given
        Person person = null;

        //when
        PersonDto result = personMapper.mapToPersonDto(person);

        //than
        assertThat(result).isNull();
    }

    @Test
       void shouldMapToEntityFromDto() {
        //given
        PersonDto dto = getPersonDto();
        Person expected = getPerson();

        //when
        Person result = personMapper.mapToPersonEntity(dto);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnNullWhenMapToEntityFromDtoWhereDtoIsNull() {
        //given
        PersonDto dto = null;
        //when
        Person person = personMapper.mapToPersonEntity(dto);

        //than
        assertThat(person).isNull();
    }

    private Person getPerson() {
        Date date = new Date(12345L);
        Department department = new Department(1L, "Department");
        Set<Language> languageList = Set.of(new Language(1L, "RU"), new Language(2L, "EU"));
        Document document = new Document("ert-234-fsd", date);

        return new Person(1L, "Oleg", "Egorov", date, department, languageList, document);
    }

    private PersonDto getPersonDto() {
        Date date = new Date(12345L);
        Set<Long> languageList = Set.of(1L, 2L);

        return new PersonDto(1L, "Oleg", "Egorov",
                date, 1L, languageList, "ert-234-fsd");
    }
}