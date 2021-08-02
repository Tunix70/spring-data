package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.mapperDto.PersonMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PersonMapperDto personMapperDto;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapperDto personMapperDto) {
        this.personRepository = personRepository;
        this.personMapperDto = personMapperDto;
    }

    public List<PersonDto> getAll(){
        List<Person> departmentList = personRepository.findAll();
        return departmentList.stream()
                .map(personMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        PersonDto dto = personMapperDto.mapToDepartmentDto(person);
        return dto;
    }

    public PersonDto save(PersonDto dto) {
        Person person = personMapperDto.mapToDepartmentEntity(dto);
        Person savePerson = personRepository.save(person);
        dto.setId(savePerson.getId());
        return dto;
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> saveList(List<Person> personList){
        return personRepository.saveAll(personList);
    }

    public void deleteList (List<Person> personList){
        personRepository.deleteAll(personList);
    }
}
