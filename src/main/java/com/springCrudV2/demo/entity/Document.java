package com.springCrudV2.demo.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "id")
    private String number;
    @Column(name = "expiry_date")
    private Date expiry_date;
    @OneToOne(mappedBy = "document" )
    private Person person;

    public Document() {
    }

    public Document(String number, Date expiry_date) {
        this.number = number;
        this.expiry_date = expiry_date;
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
                ", number='" + number + '\'' +
                ", expiry_date=" + expiry_date +
                ", person=" + person +
                '}';
    }
}
