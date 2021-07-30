package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;

public class DocumentMapperDto {
    public DocumentDto mapToDepartmentDto(Document document) {
        DocumentDto dto = new DocumentDto();

        if (document != null) {
            dto.setNumber(document.getNumber());
            dto.setExpiry_date(document.getExpiry_date());
            return dto;
        } else
            return null;
    }

    public Document mapToDepartmentEntity(DocumentDto dto) {
        Document document = new Document();

        if (dto != null) {
            document.setNumber(dto.getNumber());
            document.setExpiry_date(dto.getExpiry_date());
            return document;
        } else
            return null;
    }
}
