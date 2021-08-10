package com.springCrudV2.demo.dto;

public class LanguageDto {
    private Long id;

    private String name;

    public LanguageDto() {
    }

    public LanguageDto(Long id, String name) {
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