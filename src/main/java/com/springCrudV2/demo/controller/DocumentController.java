package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.entity.Document;
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
    public ResponseEntity<List<Document>> getAll() {
        List<Document> documentList = documentService.getAll();

        return documentList != null || !documentList.isEmpty()
                ? new ResponseEntity<>(documentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Document> getById(@PathVariable(name = "id") String id) {
        Document document = documentService.getDocumentById(id);

        return document != null
                ? new ResponseEntity<>(document, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Document> save(@RequestBody Document document) {
        documentService.save(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Document> update(@PathVariable(name = "id") @RequestBody Document document) {
        Document updateDocument = documentService.save(document);

        return updateDocument != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Document> deleteById(@PathVariable(name = "id") String id) {
        documentService.deleteByNumber(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
