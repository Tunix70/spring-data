package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LanguageMapperTest {
    private LanguageMapper languageMapper = new LanguageMapper();
    private final Long ID = 43L;
    private final String NAME = "RU";

    @Test
    void shouldMapToDtoFromEntity() {
        //given
        Language language = new Language(ID, NAME);
        LanguageDto expected = new LanguageDto(ID, NAME);

        //when
        LanguageDto result = languageMapper.mapToLanguageDto(language);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetNullWhenMapToDtoFromEntityWhereEntityIsNull() {
        //given
        Language language = null;

        //when
        LanguageDto result = languageMapper.mapToLanguageDto(language);

        //than
        assertThat(result).isNull();
    }

    @Test
    void shouldMapToEntityFromDto() {
        //given
        LanguageDto dto = new LanguageDto(ID, NAME);
        Language expected = new Language(ID, NAME);

        //when
        Language result = languageMapper.mapToLanguageEntity(dto);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnNullWhenMapToEntityFromDtoWhereDtoIsNull() {
        //given
        LanguageDto dto = null;

        //when
        Language result = languageMapper.mapToLanguageEntity(dto);

        //than
        assertThat(result).isNull();
    }
}