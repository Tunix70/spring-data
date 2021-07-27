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
//        Department department = departmentService.getDepartmentById(2L);
//        Person person = new Person(null, "Ivan", "Ivanov", date, department);
//        personService.save(person);
//        Person person1 = new Person(null, "Ivan", "Ivanov", date, department1);
//        personService.save(person1);
//        departmentService.deleteById(18L);
//        personService.deleteById(25L);
//        personService.deleteById(1L);
//        Department department = departmentService.FindByName("CRUD");
        documentService.save(new Document(null, "1fd9-paos-9999", date));
//        Person person = personService.getPersonById(1L);
//        List<Language> list = person.getLanguageList();
//        System.out.println(list);
//        languageService.save(new Language(null, "EU"));
//        Person person = personService.getPersonById(1L);
//        Language language = languageService.getLanguageById(1L);
//        Language language2 = languageService.getLanguageById(2L);
//        List<Language> languageList = new ArrayList<>();
//        languageList.add(language);
//        languageList.add(language2);
//        person.setLanguageList(languageList);
//        languageService.deleteById(1L);

    }
}
