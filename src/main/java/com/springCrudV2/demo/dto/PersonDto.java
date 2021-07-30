package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;

import java.sql.Date;
import java.util.Set;

public class PersonDto {
    private Long id;
    private String first_name;
    private String second_name;
    private Date birthday;
    private Department department;
    private Set<Language> languageList;
    private Document document;

    public PersonDto() {
    }

    public PersonDto(Long id, String first_name, String second_name, Date birthday, Department department, Set<Language> languageList, Document document) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.birthday = birthday;
        this.department = department;
        this.languageList = languageList;
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Set<Language> languageList) {
        this.languageList = languageList;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
