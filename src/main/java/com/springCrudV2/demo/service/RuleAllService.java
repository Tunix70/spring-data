package com.springCrudV2.demo.service;

import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleAllService {
    private DepartmentService departmentService;
    private PersonService personService;
    private DocumentService documentService;
    private LanguageService languageService;

    private Date date = new Date(2L);

    public RuleAllService(DepartmentService departmentService, PersonService personService, DocumentService documentService, LanguageService languageService) {
        this.departmentService = departmentService;
        this.personService = personService;
        this.documentService = documentService;
        this.languageService = languageService;
    }

    @Transactional
    public void run() {
//        departmentService.save(new Department(null, "Main Department"));
//        Department department = departmentService.getDepartmentById(1L);
//        Document document = documentService.save(new Document("eleven", date));
//        documentService.save(document);
//        Person person = new Person(null, "Kolya", "Petrov", date, department);
//        personService.save(person);

//        Person person = personService.getPersonById(2L);
//        Document document = documentService.getDocumentById("eleven");
//        person.setDocument(document);
//        personService.save(person);
//        departmentService.deleteById(18L);
        personService.deleteById(2L);
//        departmentService.deleteById(1L);
//        Department department = departmentService.FindByName("CRUD");
//        documentService.deleteByNumber("seven");

    }
}
