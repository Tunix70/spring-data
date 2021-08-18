package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DocumentMapperTest {
    private final DocumentMapper documentMapper = new DocumentMapper();
    private static final Date DATE = new Date(45654625L);
    private static final String NAME = "yer-345-tuu";

    @Test
    void shouldMapToDtoFromEntity() {
        //given
        Document document = new Document(NAME, DATE);
        DocumentDto expected = new DocumentDto(NAME, DATE);

        //when
        DocumentDto result = documentMapper.mapToDocumentDto(document);

        //than
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void shouldReturnNullWhenMapToDtoFromEntityIfDocumentIsNull() {
        //given
        Document document = null;

        //when
        DocumentDto result = documentMapper.mapToDocumentDto(document);

        //than
        assertThat(result).isNull();
    }

    @Test
    void shouldMapToEntityFromDto() {
        //given
        DocumentDto dto = new DocumentDto(NAME, DATE);
        Document expected = new Document(NAME, DATE);

        //when
        Document result = documentMapper.mapToDocumentEntity(dto);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnNullWhenMapToEntityFromDtoIfDocumentIsNull() {
        //given
        DocumentDto dto = null;

        //when
        Document result = documentMapper.mapToDocumentEntity(dto);

        //than
        assertThat(result).isNull();
    }
}