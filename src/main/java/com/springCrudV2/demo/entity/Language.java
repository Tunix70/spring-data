package com.springCrudV2.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(name = "person_language",
                joinColumns = @JoinColumn (name = "language_id"),
                inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

    public Language() {
    }

    public Language(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Language(Long id, String name, List<Person> personList) {
        this.id = id;
        this.name = name;
        this.personList = personList;
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

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personList=" + personList +
                '}';
    }
}
