package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.annotation.SmallLettersSubstring;
import com.springCrudV2.demo.annotation.StartWithCapitalLetter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

public class PersonDto {
    private Long id;

    @NotBlank(message = "Name can't be blank")
    @StartWithCapitalLetter(message = "Name must start with capital letters")
    @SmallLettersSubstring(message = "Substring(1) must contain only small letters")
    @Size(min = 2, max = 100, message = "Name length must be  between 2 and 100")
    private String first_name;

    @NotBlank(message = "Surname can't be blank")
    @StartWithCapitalLetter(message = "Surname must start with capital letters")
    @SmallLettersSubstring(message = "Surname substring(1) must contain only small letters")
    @Size(min = 2, max = 100, message = "Name length must be  between 2 and 100")
    private String second_name;

    private Date birthday;

    @NotNull(message = "Department ID can't be null")
    private Long department;

    @NotNull(message = "Language IDs can't be null")
    private Set<Long> languages;

    @NotBlank(message = "Document ID can't be blank")
    @Size(min = 10, max = 1000, message = "Name length must be  between 10 and 1000")
    private String document;

    public PersonDto() {
    }

    public PersonDto(Long id, String first_name, String second_name, Date birthday) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.birthday = birthday;
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

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public Set<Long> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Long> languages) {
        this.languages = languages;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
