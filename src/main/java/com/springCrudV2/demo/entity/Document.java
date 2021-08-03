package com.springCrudV2.demo.entity;

import javax.persistence.*;
import java.sql.Date;

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


}
