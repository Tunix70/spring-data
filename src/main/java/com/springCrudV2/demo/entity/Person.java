package com.springCrudV2.demo.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "person",
        uniqueConstraints = @UniqueConstraint(
        name = "uk_document_id",
        columnNames = {"document_id"}))
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "second_name", nullable = false)
    private String second_name;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    @ManyToMany
    @JoinTable(name = "person_language",
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_person_language_id",
                    columnNames = {"person_id", "language_id"}),
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languageList;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    public Person() {
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                ", languageList=" + languageList +
                ", document=" + document +
                '}';
    }
}
