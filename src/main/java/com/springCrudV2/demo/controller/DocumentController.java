package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDto>> getAll() {
        List<DocumentDto> dtoList = documentService.getAll();
        return dtoList != null || !dtoList.isEmpty()
                ? new ResponseEntity<>(dtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DocumentDto> getById(@PathVariable(name = "id") String id) {
        DocumentDto dto = documentService.getDocumentById(id);
        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<DocumentDto> save(@RequestBody DocumentDto dto) {
        documentService.save(dto);
        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DocumentDto> update(@PathVariable(name = "id") @RequestBody DocumentDto dto) {
        DocumentDto updateDocument = documentService.save(dto);
        return updateDocument != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DocumentDto> deleteById(@PathVariable(name = "id") String id) {
        documentService.deleteByNumber(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
