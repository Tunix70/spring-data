package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.entity.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElse(null);

    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }
}
