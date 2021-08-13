package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.mapper.DocumentMapper;
import com.springCrudV2.demo.validator.DocumentDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final DocumentDtoValidator documentDtoValidator;

    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper,
                           DocumentDtoValidator documentDtoValidator) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.documentDtoValidator = documentDtoValidator;
    }

    public List<DocumentDto> getAll() {
        List<Document> documentList = documentRepository.findAll();
        return documentList.stream()
                .map(documentMapper::mapToDocumentDto)
                .collect(Collectors.toList());
    }

    public DocumentDto getDocumentById(String id) {
        Document document = documentRepository.findById(id).orElseThrow
                (() -> new DocumentNotFoundException(id));
        return documentMapper.mapToDocumentDto(document);
    }

    public DocumentDto save(DocumentDto dto) {
        Document document = documentMapper.mapToDocumentEntity(dto);
        Document saveDocument = documentRepository.save(document);
        dto.setId(saveDocument.getId());
        return dto;
    }

    public void deleteByNumber(String id) {
        isExistById(id);
        documentRepository.deleteById(id);
    }

    public Document getEntity(DocumentDto dto) {
        return documentMapper.mapToDocumentEntity(dto);
    }

    public boolean isExistById(String id) {
        if(!documentRepository.existsById(id)) {
            throw new DocumentNotFoundException(id);
        }
        return true;
    }
}