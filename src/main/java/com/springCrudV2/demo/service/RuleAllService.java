//package com.springCrudV2.demo.service;
//
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//
//@Service
//public class RuleAllService {
//    private DepartmentService departmentService;
//    private PersonService personService;
//    private DocumentService documentService;
//    private LanguageService languageService;
//
//    private Date date = new Date(2L);
//
//    public RuleAllService(DepartmentService departmentService, PersonService personService, DocumentService documentService, LanguageService languageService) {
//        this.departmentService = departmentService;
//        this.personService = personService;
//        this.documentService = documentService;
//        this.languageService = languageService;
//    }
//Initialization block
//    public void run() {
//        Document document1 = documentService.save(new Document("pass-012-213", date));
//        Document document2 = documentService.save(new Document("pass-123-444", date));
//        Document document3 = documentService.save(new Document("pass-645-987", date));
//        Document document4 = documentService.save(new Document("pass-015-567", date));
//
//        Department department1 = departmentService.save(new Department("Main Department"));
//        Department department2 = departmentService.save(new Department("Sport Department"));
//        Department department3 = departmentService.save(new Department("Snoop Department"));
//        Department department4 = departmentService.save(new Department("Simple Department"));
//
//        Language language1 = languageService.save(new Language(null, "English"));
//        Language language2 = languageService.save(new Language(null, "Russian"));
//        Language language3 = languageService.save(new Language(null, "German"));
//        Language language4 = languageService.save(new Language(null, "Italian"));
//
//        Set<Language> languageList1 = new HashSet<>();
//        languageList1.add(language1);
//        languageList1.add(language2);
//        languageList1.add(language4);
//
//        Set<Language> languageList2 = new HashSet<>();
//        languageList2.add(language3);
//        languageList2.add(language1);
//
//        Set<Language> languageList3 = new HashSet<>();
//        languageList3.add(language3);
//        languageList3.add(language4);
//
//        Set<Language> languageList4 = new HashSet<>();
//        languageList4.add(language1);
//        languageList4.add(language4);
//
//        Person person1 = personService.save(new Person(null, "Ivan", "Ivanov", date, department1));
//        person1.setLanguageList(languageList1);
//        person1.setDocument(document1);
//        personService.save(person1);
//
//        Person person2 = personService.save(new Person(null, "Nikolay", "Panfilov", date, department2));
//        person2.setLanguageList(languageList2);
//        person2.setDocument(document2);
//        personService.save(person2);
//
//        Person person3 = personService.save(new Person(null, "Valera", "VALERAA", date, department3));
//        person3.setLanguageList(languageList3);
//        person3.setDocument(document3);
//        personService.save(person3);
//
//        Person person4 = personService.save(new Person(null, "Olya", "Pavlova", date, department4));
//        person4.setLanguageList(languageList4);
//        person4.setDocument(document4);
//        personService.save(person4);
//    }
//}
