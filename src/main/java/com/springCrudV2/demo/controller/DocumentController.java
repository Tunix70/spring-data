package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<List<DocumentDto>> getAll() {
        List<DocumentDto> dtoList = documentService.getAll();
        return dtoList != null
                ? new ResponseEntity<>(dtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<DocumentDto> getById(@PathVariable(name = "id") @NotBlank String id) {
        DocumentDto dto = documentService.getDocumentById(id);
        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<DocumentDto> save(@Valid @RequestBody DocumentDto dto) {
        documentService.save(dto);
        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<DocumentDto> update(@Valid @RequestBody DocumentDto dto) {
        DocumentDto updateDocument = documentService.save(dto);
        return updateDocument != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<DocumentDto> deleteById(@PathVariable(name = "id") @NotBlank String id) {
        documentService.deleteByNumber(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
