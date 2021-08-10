package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LanguageMapperDtoTest {
    private LanguageMapperDto languageMapperDto = new LanguageMapperDto();

    @Test
    @DisplayName("Method should map from entity to dto")
    void shouldMapToDtoFromEntity() {
        //given
        Language language = new Language(43L, "RU");
        Language language1 = null;

        //when
        LanguageDto dto = languageMapperDto.mapToLanguageDto(language);
        LanguageDto dto1 = languageMapperDto.mapToLanguageDto(language1);

        //than
        assertThat(dto.getId()).isEqualTo(language.getId());
        assertThat(dto.getName()).isEqualTo(language.getName());

        assertThat(dto1).isNull();
    }

    @Test
    @DisplayName("Method should map from dto to entity")
    void shouldMapToEntityFromDto() {
        //given
        LanguageDto dto = new LanguageDto(54L, "RU");
        LanguageDto dto1 = null;

        //when
        Language language = languageMapperDto.mapToLanguageEntity(dto);
        Language language1 = languageMapperDto.mapToLanguageEntity(dto1);

        //than
        assertThat(language.getId()).isEqualTo(dto.getId());
        assertThat(language.getName()).isEqualTo(dto.getName());

        assertThat(language1).isNull();
    }
}