package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LanguageMapperTest {
    private LanguageMapper languageMapper = new LanguageMapper();

    @Test
    @DisplayName("Method should map from entity to dto")
    void shouldMapToDtoFromEntity() {
        //given
        Language language = new Language(43L, "RU");
        Language language1 = null;

        //when
        LanguageDto dto = languageMapper.mapToLanguageDto(language);
        LanguageDto dto1 = languageMapper.mapToLanguageDto(language1);

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
        Language language = languageMapper.mapToLanguageEntity(dto);
        Language language1 = languageMapper.mapToLanguageEntity(dto1);

        //than
        assertThat(language.getId()).isEqualTo(dto.getId());
        assertThat(language.getName()).isEqualTo(dto.getName());

        assertThat(language1).isNull();
    }
}