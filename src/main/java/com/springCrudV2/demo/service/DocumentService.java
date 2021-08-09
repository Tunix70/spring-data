package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.exception.DocumentInvalidDateException;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.mapperDto.DocumentMapperDto;
import com.springCrudV2.demo.validator.DocumentDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapperDto documentMapperDto;
    private final DocumentDtoValidator documentDtoValidator;

    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapperDto documentMapperDto,
                           DocumentDtoValidator documentDtoValidator) {
        this.documentRepository = documentRepository;
        this.documentMapperDto = documentMapperDto;
        this.documentDtoValidator = documentDtoValidator;
    }

    public List<DocumentDto> getAll() {
        List<Document> documentList = documentRepository.findAll();
        return documentList.stream()
                .map(documentMapperDto::mapToDocumentDto)
                .collect(Collectors.toList());
    }

    public DocumentDto getDocumentById(String id) {
        Document document = documentRepository.getById(id);
        try {
            document.getExpiry_date();
        } catch (EntityNotFoundException e) {
            throw new DocumentNotFoundException(id);
        }
        return documentMapperDto.mapToDocumentDto(document);
    }

    public DocumentDto save(DocumentDto dto) {
        validate(dto);
        Document document = documentMapperDto.mapToDocumentEntity(dto);
        Document saveDocument = documentRepository.save(document);
        dto.setId(saveDocument.getId());
        return dto;
    }

    public void deleteByNumber(String id) {
        if (isExist(id))
            documentRepository.deleteById(id);
    }

    public Document getEntity(DocumentDto dto) {
        return documentMapperDto.mapToDocumentEntity(dto);
    }

    public void validate(DocumentDto dto) {
        final DataBinder dataBinder = new DataBinder(dto);
        dataBinder.addValidators(documentDtoValidator);
        dataBinder.validate(dto);
        if (dataBinder.getBindingResult().hasErrors()) {
            String errorsMessage = dataBinder.getBindingResult().getAllErrors().stream().
                    map(e -> messageSource.getMessage(e, Locale.getDefault()))
                    .collect(Collectors.joining("\n"));
            throw new DocumentInvalidDateException(errorsMessage);
        }
    }

    public boolean isValidId(String id) {
        if (id == null || id.isBlank())
            throw new DocumentNotFoundException(id);
        return true;
    }

    public boolean isExist(String id) {
        return getDocumentById(id) != null;
    }
}