package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.mapperDto.DocumentMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentDto dto;
    private DocumentMapperDto documentMapperDto = new DocumentMapperDto();
    private List<DocumentDto> documentDtos;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentDto> getAll() {
        List<Document> documentList = documentRepository.findAll();
        documentDtos = documentList.stream()
                .map(documentMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
        return documentDtos;
    }

    public DocumentDto getDocumentById(String number) {
        Document document = documentRepository.getByNumber(number);
        dto = documentMapperDto.mapToDepartmentDto(document);
        return dto;

    }

    public DocumentDto save(DocumentDto documentDto) {
        Document document = documentMapperDto.mapToDepartmentEntity(documentDto);
        documentRepository.save(document);
        return documentDto;
    }

    public void deleteByNumber(String number) {
        documentRepository.deleteByNumber(number);
    }
}
