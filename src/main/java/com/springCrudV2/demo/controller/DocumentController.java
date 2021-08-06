package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDto>> getAll() {
        List<DocumentDto> dtoList = documentService.getAll();
        return dtoList != null || !dtoList.isEmpty()
                ? new ResponseEntity<>(dtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DocumentDto> getById(@PathVariable(name = "id") @NotBlank String id) {
        return new ResponseEntity<>(documentService.getDocumentById(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DocumentDto> save(@Valid @RequestBody DocumentDto dto) {
        return new ResponseEntity<>(documentService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<DocumentDto> update(@Valid @RequestBody DocumentDto dto) {
        documentService.isValidId(dto.getId());
        return new ResponseEntity<>(documentService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DocumentDto> deleteById(@PathVariable(name = "id") @NotBlank String id) {
        documentService.deleteByNumber(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
