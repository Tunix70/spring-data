package com.springCrudV2.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    private Long id;
    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 100, message = "Name length must be  between 2 and 100")
    private String name;

    public UserDto() {
    }

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
