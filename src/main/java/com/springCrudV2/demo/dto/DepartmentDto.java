package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.annotation.StartWithCapitalLetter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Can't be blank")
    @StartWithCapitalLetter
    @Length(min = 1, max = 150, message = "Length must be between 1 and 150")
    private String name;

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
}