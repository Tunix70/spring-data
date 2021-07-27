package com.springCrudV2.demo.dao;

import com.springCrudV2.demo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Document getByNumber (String number);
    void deleteByNumber(String number);
}
