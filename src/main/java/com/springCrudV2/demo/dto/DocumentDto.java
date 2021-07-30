package com.springCrudV2.demo.dto;

import java.sql.Date;

public class DocumentDto {
    private String number;
    private Date expiry_date;


    public DocumentDto() {
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

}
