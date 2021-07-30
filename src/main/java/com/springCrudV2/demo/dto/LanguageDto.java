package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.entity.Person;

import java.util.Set;

public class LanguageDto {
    private Long id;
    private String name;
    private Set<Person> personList;

    public LanguageDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(Set<Person> personList) {
        this.personList = personList;
    }
}
