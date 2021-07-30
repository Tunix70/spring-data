package com.springCrudV2.demo.dto;

import java.sql.Date;
import java.util.Set;

public class PersonDto {
    private Long id;
    private String first_name;
    private String second_name;
    private Date birthday;
    private DepartmentDto department;
    private Set<LanguageDto> languages;
    private DocumentDto document;

    public PersonDto() {
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

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public Set<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageDto> languages) {
        this.languages = languages;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }
}
