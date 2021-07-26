package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonSerice {
    private PersonRepository personRepository;

    public PersonSerice(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Person getDepartmentById(Long id) {
        return personRepository.findById(id).orElse(null);

    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
