package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.exception.*;
import com.springCrudV2.demo.mapperDto.PersonMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        isValidId(id);
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return personMapperDto.mapToPersonDto(person);
    }

    public PersonDto save(PersonDto dto) {
        isValidDto(dto);
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
        person.setDocument(documentService.getEntity(documentDto));

        DepartmentDto departmentDto = departmentService.getDepartmentById(dto.getDepartment());
        person.setDepartment(departmentService.getEntity(departmentDto));

        Set<Language> dtoSet = dto.getLanguages().stream()
                .map(languageService::getLanguageById)
                .map(languageService::getEntity)
                .collect(Collectors.toSet());

        person.setLanguageList(dtoSet);
        return person;
    }

    public boolean isValidId(Long id) {
        if (id == null || id <= 1L || personRepository.existsById(id))
            throw new DepartmentNotFoundException(id);
        return true;
    }

    public boolean isValidDto(PersonDto dto) {
        if (!documentService.isExist(dto.getDocument()))
            throw new DocumentNotFoundException(dto.getDocument());
        if (!departmentService.isExistById(dto.getDepartment()))
            throw new DepartmentNotFoundException(dto.getDepartment());
        for (Long language : dto.getLanguages()) {
            if (!languageService.isExist(languageService.getLanguageById(language)))
                throw new LanguageNotFoundException(language);
        }
        List<Person> list = personRepository.findAll();
        for (Person person : list) {
            if (person != null && person.getDocument().getId().equals(dto.getDocument()))
                throw new DocumentAlreadyTakenException();
        }
        return true;
    }
}