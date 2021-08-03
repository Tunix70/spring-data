package com.springCrudV2.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "language",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))

public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(name = "person_language",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> personList;

    public Language() {
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
