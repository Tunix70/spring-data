package com.springCrudV2.demo.entity;

import com.springCrudV2.demo.model.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "city", nullable = false)
    private String city;

    public User() {
    }

    public User(Long id, String name, String password, Role role, String city) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && role == user.role && Objects.equals(city, user.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, role, city);
    }
}
