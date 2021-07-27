package com.springCrudV2.demo.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "expiry_date")
    private Date expiry_date;
    @OneToOne(optional = false, mappedBy = "document")
    private Person person;

    public Document() {
    }

    public Document(Long id, String number, Date expiry_date) {
        this.id = id;
        this.number = number;
        this.expiry_date = expiry_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expiry_date=" + expiry_date +
                ", person=" + person +
                '}';
    }
}
