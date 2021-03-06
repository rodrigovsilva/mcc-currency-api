package com.rvfs.challenge.mcc.currency.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rvfs.challenge.mcc.currency.util.serializer.CalendarSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Users data transfer objetcs.
 */
public class UserDTO implements Serializable{

    private Long id;

    private String email;

    @JsonSerialize(using = CalendarSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Calendar birth;

    private PostalAddressDTO address;

    public UserDTO(Long id, String email, Calendar birth, PostalAddressDTO address) {
        this.id = id;
        this.email = email;
        this.birth = birth;
        this.address = address;
    }

    public UserDTO(Long id, String email, Calendar birth) {
        this.id = id;
        this.email = email;
        this.birth = birth;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public PostalAddressDTO getAddress() {
        return address;
    }

    public void setAddress(PostalAddressDTO address) {
        this.address = address;
    }
}
