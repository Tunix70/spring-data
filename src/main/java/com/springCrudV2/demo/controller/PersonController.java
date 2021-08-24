package com.springCrudV2.demo.controller;


import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> personList = personService.getAll();
        return personList != null
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<PersonDto> getById(@PathVariable("id") @NotNull Long id) {
        PersonDto person = personService.getPersonById(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        PersonDto savePerson = personService.save(personDto);
        return personDto != null
                ? new ResponseEntity<>(savePerson, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto personDto) {
        PersonDto updatePerson = personService.save(personDto);
        return updatePerson != null
                ? new ResponseEntity<>(updatePerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Person> deleteById(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
