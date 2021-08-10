package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DocumentMapperDtoTest {
    private DocumentMapperDto documentMapperDto = new DocumentMapperDto();
    private Date date = new Date(45654625L);

    @Test
    @DisplayName("Method should convert from entity to dto")
    void shouldMapToDtoFromEntity() {
        //given
        Document document = new Document("yer-345-tuu", date);
        Document document1 = null;

        //when
        DocumentDto dto = documentMapperDto.mapToDocumentDto(document);
        DocumentDto dto1 = documentMapperDto.mapToDocumentDto(document1);

        //than
        assertThat(dto.getId()).isEqualTo(document.getId());
        assertThat(dto.getExpiry_date()).isEqualTo(document.getExpiry_date());

        assertThat(dto1).isNull();
    }

    @Test
    @DisplayName("Method should convert from dto to entity")
    void shouldMapToEntityFromDto() {
        //given
        DocumentDto dto = new DocumentDto("wer-123-iro", date);
        DocumentDto dto1 = null;

        //when
        Document document = documentMapperDto.mapToDocumentEntity(dto);
        Document document1 = documentMapperDto.mapToDocumentEntity(dto1);

        //than
        assertThat(document.getId()).isEqualTo(dto.getId());
        assertThat(document.getExpiry_date()).isEqualTo(dto.getExpiry_date());

        assertThat(document1).isNull();
    }
}