package com.springCrudV2.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

public class DocumentDto {
    @NotBlank(message = "ID can't be blank")
    @Size(min = 10, max = 1000, message = "Name length must be  between 10 and 1000")
    private String id;
    private Date expiry_date;

    public DocumentDto() {
    }

    public DocumentDto(String id, Date expiry_date) {
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
}