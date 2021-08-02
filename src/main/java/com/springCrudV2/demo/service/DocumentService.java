package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.mapperDto.DocumentMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentMapperDto documentMapperDto;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapperDto documentMapperDto) {
        this.documentRepository = documentRepository;
        this.documentMapperDto = documentMapperDto;
    }

    public List<DocumentDto> getAll() {
        List<Document> documentList = documentRepository.findAll();
        return documentList.stream()
                .map(documentMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public DocumentDto getDocumentById(String id) {
        Document document = documentRepository.getById(id);
        return documentMapperDto.mapToDepartmentDto(document);

    }

    public DocumentDto save(DocumentDto dto) {
        Document document = documentMapperDto.mapToDepartmentEntity(dto);
        Document saveDocument = documentRepository.save(document);
        dto.setId(saveDocument.getId());
        return dto;
    }

    public void deleteByNumber(String id) {
        documentRepository.deleteById(id);
    }
}
