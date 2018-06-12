package com.rvfs.challenge.mcc.currency.dto;

import java.io.Serializable;

/**
 * Users data transfer objetcs.
 */
public class UserDTO implements Serializable{

    private String email;

    private String birth;

    private PostalAddressDTO address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public PostalAddressDTO getAddress() {
        return address;
    }

    public void setAddress(PostalAddressDTO address) {
        this.address = address;
    }
}
