package com.springCrudV2.demo.dto;

import java.sql.Date;

public class DocumentDto {
    private String id;
    private Date expiry_date;


    public DocumentDto() {
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
