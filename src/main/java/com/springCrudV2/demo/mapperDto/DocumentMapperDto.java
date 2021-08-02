package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;

public class DocumentMapperDto {
    public DocumentDto mapToDepartmentDto(Document document) {
        DocumentDto dto = new DocumentDto();

        if (document != null) {
            dto.setId(document.getId());
            dto.setExpiry_date(document.getExpiry_date());
//            dto.setPerson(document.getPerson());
            return dto;
        } else
            return null;
    }

    public Document mapToDepartmentEntity(DocumentDto dto) {
        Document document = new Document();

        if (dto != null) {
            document.setId(dto.getId());
            document.setExpiry_date(dto.getExpiry_date());
            document.setPerson(dto.getPerson());
            return document;
        } else
            return null;
    }
}
