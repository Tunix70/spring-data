package com.springCrudV2.demo.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "person")
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

    public Person() {
    }

    public Person(Long id, String first_name, String second_name, Date birthday, Department department) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.birthday = birthday;
        this.department = department;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
