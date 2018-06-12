package com.rvfs.challenge.mcc.currency.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Users data transfer objetcs.
 */
@Entity
@Table(name = "User")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    private String email;

    private Calendar birth;

    @OneToOne(cascade = {CascadeType.ALL})
    private PostalAddress address;

    public User(Long id, String email, Calendar birth, PostalAddress address) {
        this.email = email;
        this.birth = birth;
        this.address = address;
    }

    public User(Long id, String email, Calendar birth) {
        this.email = email;
        this.birth = birth;
    }

    public User() {
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

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }
}
