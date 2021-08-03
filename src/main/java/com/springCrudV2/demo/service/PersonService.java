package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.exception.NotFoundLanguageException;
import com.springCrudV2.demo.mapperDto.PersonMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final DocumentService documentService;
    private final DepartmentService departmentService;
    private final LanguageService languageService;
    private final PersonMapperDto personMapperDto;

    @Autowired
    public PersonService(PersonRepository personRepository, DocumentService documentService,
                         DepartmentService departmentService, LanguageService languageService, PersonMapperDto personMapperDto) {
        this.personRepository = personRepository;
        this.documentService = documentService;
        this.departmentService = departmentService;
        this.languageService = languageService;
        this.personMapperDto = personMapperDto;
    }

    public List<PersonDto> getAll() {
        List<Person> departmentList = personRepository.findAll();
        return departmentList.stream()
                .map(personMapperDto::mapToPersonDto)
                .collect(Collectors.toList());
    }

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        return personMapperDto.mapToPersonDto(person);
    }

    public PersonDto save(PersonDto dto) {
            Person person = fillFieldsPerson(dto);
            Person savePerson = personRepository.save(person);
            dto.setId(savePerson.getId());
        return dto;
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Person fillFieldsPerson(PersonDto dto) {
        Person person = personMapperDto.mapToPersonEntity(dto);

        DocumentDto documentDto = documentService.getDocumentById(dto.getDocument());
        person.setDocument(documentService.getEntyty(documentDto));

        DepartmentDto departmentDto = departmentService.getDepartmentById(dto.getDepartment());
        person.setDepartment(departmentService.getEntity(departmentDto));

        Set<Language> dtoSet = dto.getLanguages().stream()
                .map(languageService::getLanguageById)
                .map(languageService::getEntity)
                .collect(Collectors.toSet());

        person.setLanguageList(dtoSet);
        return person;
    }
}