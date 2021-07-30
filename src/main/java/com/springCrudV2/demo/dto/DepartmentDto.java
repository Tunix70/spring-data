package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.entity.Person;

import java.util.List;

public class DepartmentDto {
    private Long id;
    private String name;
    private List<Person> personList;

    public DepartmentDto() {
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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
