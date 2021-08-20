package com.springCrudV2.demo.dto;

import com.springCrudV2.demo.model.Role;

public class UserSaveDto {
    private Long id;
    private String name;
    private String password;
    private Role role;
    private String city;

    public UserSaveDto() {
    }

    public UserSaveDto(Long id, String name, String password, Role role, String city) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.city = city;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
