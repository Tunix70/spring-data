package com.springCrudV2.demo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "expiry_date")
    private Date expiry_date;
    @OneToOne(mappedBy = "document")
    private Person person;

    public Document() {
    }

    public Document(String id, Date expiry_date) {
        this.id = id;
        this.expiry_date = expiry_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return id.equals(document.id) && expiry_date.equals(document.expiry_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expiry_date);
    }
}
