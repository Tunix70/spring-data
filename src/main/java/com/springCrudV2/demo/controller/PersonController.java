package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> personList = personService.getAll();
        return personList != null
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getById(@PathVariable("id") @NotNull Long id) {
        PersonDto person = personService.getPersonById(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        PersonDto savePerson = personService.save(personDto);
        return personDto != null
                ? new ResponseEntity<>(savePerson, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto personDto) {
        PersonDto updatePerson = personService.save(personDto);
        return updatePerson != null
                ? new ResponseEntity<>(updatePerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> deleteById(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
