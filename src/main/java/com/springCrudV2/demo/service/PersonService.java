package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.exception.PersonNotFoundException;
import com.springCrudV2.demo.mapper.PersonMapper;
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
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, DocumentService documentService,
                         DepartmentService departmentService, LanguageService languageService, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.documentService = documentService;
        this.departmentService = departmentService;
        this.languageService = languageService;
        this.personMapper = personMapper;
    }

    public List<PersonDto> getAll() {
        List<Person> departmentList = personRepository.findAll();
        return departmentList.stream()
                .map(personMapper::mapToPersonDto)
                .collect(Collectors.toList());
    }

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.mapToPersonDto(person);
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
        Person person = personMapper.mapToPersonEntity(dto);

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

    public boolean isValidDto(PersonDto dto) {
        if (!documentService.isExistById(dto.getDocument()))
            throw new DocumentNotFoundException(dto.getDocument());
        if (!departmentService.isExistById(dto.getDepartment()))
            throw new DepartmentNotFoundException(dto.getDepartment());
        for (Long language : dto.getLanguages()) {
            if (!languageService.isExistById(language))
                throw new LanguageNotFoundException(language);
        }
        return true;
    }
}