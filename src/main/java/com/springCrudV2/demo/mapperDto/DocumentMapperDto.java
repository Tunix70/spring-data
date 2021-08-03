package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapperDto {
    public DocumentDto mapToDocumentDto(Document document) {
        DocumentDto dto = new DocumentDto();

        if (document != null) {
            dto.setId(document.getId());
            dto.setExpiry_date(document.getExpiry_date());
            return dto;
        } else
            return null;
    }

    public Document mapToDocumentEntity(DocumentDto dto) {
        Document document = new Document();

        if (dto != null) {
            document.setId(dto.getId());
            document.setExpiry_date(dto.getExpiry_date());
            return document;
        } else
            return null;
    }
}
